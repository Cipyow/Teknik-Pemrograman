import java.util.*;
import java.util.concurrent.*;
import java.time.LocalDateTime;

// Records untuk data immutable
record Pesawat(String id, String nama, String tipe, int kapasitas) {}
record Penerbangan(
    String nomorPenerbangan,
    Pesawat pesawat,
    String asal,
    String tujuan,
    LocalDateTime waktuBerangkat,
    Status status
) {}

// Enum untuk status
enum Status { TERJADWAL, BERANGKAT, TERBANG, MENDARAT, SELESAI }

class SistemPenerbangan {
    // Concurrent Collections
    private final ConcurrentHashMap<String, Pesawat> daftarPesawat = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Penerbangan> penerbanganAktif = new ConcurrentHashMap<>();
    
    // Queue dan Deque
    private final Deque<Penerbangan> antrianKeberangkatan = new ConcurrentLinkedDeque<>();
    private final Deque<Penerbangan> antrianKedatangan = new ConcurrentLinkedDeque<>();
    
    // Set
    private final Set<String> bandaraAktif = ConcurrentHashMap.newKeySet();
    
    // Immutable Collections
    private final List<String> daftarBandara = List.of("CGK", "DPS", "SUB", "MES", "UPG");
    private final Set<String> tipeKhusus = Set.of("CARGO", "MILITER", "VIP");
    private final Map<String, Integer> kapasitasBandara = Map.of(
        "CGK", 100,
        "DPS", 50,
        "SUB", 40,
        "MES", 30,
        "UPG", 25
    );

    private final List<Penerbangan> riwayatPenerbangan = Collections.synchronizedList(new ArrayList<>());
    private final List<String> logAktivitas = Collections.synchronizedList(new ArrayList<>());

    // Mendaftarkan pesawat baru
    public void daftarPesawat(Pesawat pesawat) {
        validasiTipePesawat(pesawat);
        daftarPesawat.put(pesawat.id(), pesawat);
    }

    private void validasiTipePesawat(Pesawat pesawat) {
        if (tipeKhusus.contains(pesawat.tipe().toUpperCase())) {
            throw new IllegalArgumentException(
                "Tipe pesawat " + pesawat.tipe() + " memerlukan izin khusus (CARGO/MILITER/VIP)"
            );
        }
    }

    // Mendapatkan informasi pesawat
    public Optional<Pesawat> getPesawat(String id) {
        return Optional.ofNullable(daftarPesawat.get(id));
    }

    // Menjadwalkan penerbangan
    public void jadwalkanPenerbangan(Penerbangan penerbangan) {
        validasiPenerbangan(penerbangan);
        penerbanganAktif.put(penerbangan.nomorPenerbangan(), penerbangan);
        antrianKeberangkatan.offer(penerbangan);
    }

    private void validasiPenerbangan(Penerbangan penerbangan) {
        if (!daftarBandara.contains(penerbangan.asal()) || 
            !daftarBandara.contains(penerbangan.tujuan())) {
            throw new IllegalArgumentException("Bandara tidak valid");
        }

        if (!daftarPesawat.containsKey(penerbangan.pesawat().id())) {
            throw new IllegalArgumentException("Pesawat tidak terdaftar");
        }

        if (bandaraAktif.size() >= kapasitasBandara.get(penerbangan.asal())) {
            throw new IllegalArgumentException("Bandara penuh");
        }
    }

    // Update status penerbangan
    public void updateStatus(String nomorPenerbangan, Status statusBaru) {
        Penerbangan penerbangan = penerbanganAktif.get(nomorPenerbangan);
        if (penerbangan == null) {
            throw new IllegalArgumentException("Penerbangan tidak ditemukan");
        }

        Penerbangan penerbanganUpdate = new Penerbangan(
            penerbangan.nomorPenerbangan(),
            penerbangan.pesawat(),
            penerbangan.asal(),
            penerbangan.tujuan(),
            penerbangan.waktuBerangkat(),
            statusBaru
        );

        penerbanganAktif.put(nomorPenerbangan, penerbanganUpdate);
        updateAntrian(penerbanganUpdate);

        // Menambah log aktivitas
        String log = String.format("[%s] Penerbangan %s: %s -> %s",
            LocalDateTime.now(), nomorPenerbangan, penerbangan.status(), statusBaru);
        logAktivitas.add(log);

        // Jika status SELESAI, tambahkan ke riwayat
        if (statusBaru == Status.SELESAI) {
            riwayatPenerbangan.add(penerbanganUpdate);
        }
    }

    private void updateAntrian(Penerbangan penerbangan) {
        switch (penerbangan.status()) {
            case TERJADWAL -> antrianKeberangkatan.offerLast(penerbangan);
            case BERANGKAT -> {
                antrianKeberangkatan.remove(penerbangan);
                bandaraAktif.add(penerbangan.asal());
            }
            case TERBANG -> {
                bandaraAktif.remove(penerbangan.asal());
                antrianKedatangan.offerLast(penerbangan);
            }
            case MENDARAT -> {
                antrianKedatangan.remove(penerbangan);
                bandaraAktif.add(penerbangan.tujuan());
            }
            case SELESAI -> {
                bandaraAktif.remove(penerbangan.tujuan());
                penerbanganAktif.remove(penerbangan.nomorPenerbangan());
            }
        }
    }

    public List<Penerbangan> getPenerbanganAktif() {
        return new ArrayList<>(penerbanganAktif.values());
    }

    public List<Penerbangan> getRiwayatPenerbangan() {
        return new ArrayList<>(riwayatPenerbangan);
    }

    public List<String> getLogAktivitas() {
        return new ArrayList<>(logAktivitas);
    }
}

public class SimplifiedAirTrafficSystem {
    private static SistemPenerbangan sistem;
    private static Scanner scanner;

    public static void main(String[] args) {
        initializeSystem();
        scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getIntInput("Pilih menu (0-6): ");

            switch (choice) {
                case 1 -> daftarPesawatBaru();
                case 2 -> jadwalkanPenerbangan();
                case 3 -> updateStatusPenerbangan();
                case 4 -> lihatPenerbanganAktif();
                case 5 -> lihatRiwayatPenerbangan();
                case 6 -> lihatLogAktivitas();
                case 0 -> running = false;
                default -> System.out.println("Menu tidak valid!");
            }
        }
        scanner.close();
    }

    private static void initializeSystem() {
        sistem = new SistemPenerbangan();
        // Data sampel
        Pesawat pesawat1 = new Pesawat("GA001", "Garuda Indonesia", "Boeing 737", 180);
        Pesawat pesawat2 = new Pesawat("LA001", "Lion Air", "Airbus A320", 190);
        
        sistem.daftarPesawat(pesawat1);
        sistem.daftarPesawat(pesawat2);
    }

    private static void displayMenu() {
        System.out.println("\n=== SISTEM PENERBANGAN ===");
        System.out.println("1. Daftar Pesawat Baru");
        System.out.println("2. Jadwalkan Penerbangan");
        System.out.println("3. Update Status Penerbangan");
        System.out.println("4. Lihat Penerbangan Aktif");
        System.out.println("5. Lihat Riwayat Penerbangan");
        System.out.println("6. Lihat Log Aktivitas");
        System.out.println("0. Keluar");
        System.out.println("========================");
    }

    private static void daftarPesawatBaru() {
        System.out.println("\n=== DAFTAR PESAWAT BARU ===");
        System.out.print("ID Pesawat: ");
        String id = scanner.nextLine().toUpperCase();
        System.out.print("Nama Maskapai: ");
        String nama = scanner.nextLine();
        System.out.print("Tipe Pesawat: ");
        String tipe = scanner.nextLine();
        System.out.print("Kapasitas: ");
        int kapasitas = Integer.parseInt(scanner.nextLine());

        Pesawat pesawat = new Pesawat(id, nama, tipe, kapasitas);
        sistem.daftarPesawat(pesawat);
        System.out.println("Pesawat berhasil didaftarkan!");
    }

    private static void jadwalkanPenerbangan() {
        System.out.println("\n=== JADWALKAN PENERBANGAN ===");
        System.out.print("Nomor Penerbangan: ");
        String nomor = scanner.nextLine().toUpperCase();
        System.out.print("ID Pesawat: ");
        String idPesawat = scanner.nextLine().toUpperCase();

        Optional<Pesawat> pesawat = sistem.getPesawat(idPesawat);
        if (pesawat.isEmpty()) {
            System.out.println("Pesawat tidak ditemukan!");
            return;
        }

        System.out.print("Bandara Asal (CGK/DPS/SUB/MES/UPG): ");
        String asal = scanner.nextLine().toUpperCase();
        System.out.print("Bandara Tujuan (CGK/DPS/SUB/MES/UPG): ");
        String tujuan = scanner.nextLine().toUpperCase();

        try {
            Penerbangan penerbangan = new Penerbangan(
                nomor,
                pesawat.get(),
                asal,
                tujuan,
                LocalDateTime.now(),
                Status.TERJADWAL
            );
            sistem.jadwalkanPenerbangan(penerbangan);
            System.out.println("Penerbangan berhasil dijadwalkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateStatusPenerbangan() {
        System.out.println("\n=== UPDATE STATUS PENERBANGAN ===");
        System.out.print("Nomor Penerbangan: ");
        String nomor = scanner.nextLine().toUpperCase();
        System.out.println("Status Baru (TERJADWAL/BERANGKAT/TERBANG/MENDARAT/SELESAI): ");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        try {
            sistem.updateStatus(nomor, status);
            System.out.println("Status penerbangan berhasil diupdate!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void lihatPenerbanganAktif() {
        System.out.println("\n=== PENERBANGAN AKTIF ===");
        List<Penerbangan> penerbangan = sistem.getPenerbanganAktif();
        if (penerbangan.isEmpty()) {
            System.out.println("Tidak ada penerbangan aktif");
            return;
        }

        penerbangan.forEach(p -> {
            System.out.println("\nNomor: " + p.nomorPenerbangan());
            System.out.println("Maskapai: " + p.pesawat().nama());
            System.out.println("Rute: " + p.asal() + " -> " + p.tujuan());
            System.out.println("Status: " + p.status());
        });
    }

    // Method baru untuk melihat riwayat penerbangan
    private static void lihatRiwayatPenerbangan() {
        System.out.println("\n=== RIWAYAT PENERBANGAN ===");
        List<Penerbangan> riwayat = sistem.getRiwayatPenerbangan();
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada riwayat penerbangan");
            return;
        }

        riwayat.forEach(p -> {
            System.out.println("\nNomor: " + p.nomorPenerbangan());
            System.out.println("Maskapai: " + p.pesawat().nama());
            System.out.println("Rute: " + p.asal() + " -> " + p.tujuan());
            System.out.println("Waktu Berangkat: " + p.waktuBerangkat());
            System.out.println("Status: " + p.status());
        });
    }

    // Method baru untuk melihat log aktivitas
    private static void lihatLogAktivitas() {
        System.out.println("\n=== LOG AKTIVITAS SISTEM ===");
        List<String> logs = sistem.getLogAktivitas();
        if (logs.isEmpty()) {
            System.out.println("Belum ada aktivitas tercatat");
            return;
        }

        logs.forEach(System.out::println);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Mohon masukkan angka yang valid!");
            }
        }
    }
} 