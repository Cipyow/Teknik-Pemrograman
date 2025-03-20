import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bioskop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Film film1 = new Film("The Avengers", "Action", 143, 50000, Arrays.asList("10:00", "14:00", "18:00"));
            Film film2 = new Film("Despicable Me", "Animation", 95, 40000, Arrays.asList("09:00", "13:00", "17:00"));
            Film film3 = new Film("John Wick", "Thriller", 101, 45000, Arrays.asList("12:00", "16:00", "20:00"));
            Film[] daftarFilm = {film1, film2, film3};

            System.out.println("=== Daftar Film ===");
            for (int i = 0; i < daftarFilm.length; i++) {
                System.out.println((i + 1) + ". " + daftarFilm[i]);
            }

            System.out.print("\nMasukkan nama pelanggan: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan No HP pelanggan: ");
            String noHP = scanner.nextLine();
            Pelanggan pelanggan = new Pelanggan(nama, noHP);

            System.out.print("\nPilih nomor film: ");
            int pilihanFilm = Integer.parseInt(scanner.nextLine());
            if (pilihanFilm < 1 || pilihanFilm > daftarFilm.length) {
                System.out.println("Pilihan film tidak valid.");
                return; 
            }
            Film filmTerpilih = daftarFilm[pilihanFilm - 1];

            List<String> jadwalTersedia = filmTerpilih.getJadwalTayang();
            System.out.println("\nJadwal tayang untuk " + filmTerpilih.getJudul() + ":");
            for (int i = 0; i < jadwalTersedia.size(); i++) {
                System.out.println((i + 1) + ". " + jadwalTersedia.get(i));
            }

            System.out.print("Pilih nomor jadwal tayang: ");
            int pilihanJadwal = Integer.parseInt(scanner.nextLine());
            if (pilihanJadwal < 1 || pilihanJadwal > jadwalTersedia.size()) {
                System.out.println("Pilihan jadwal tidak valid.");
                return;
            }
            String jadwalTerpilih = jadwalTersedia.get(pilihanJadwal - 1);

            Tiket tiket = new Tiket(filmTerpilih, jadwalTerpilih);

            System.out.print("Masukkan jumlah tiket: ");
            int jumlahTiket = Integer.parseInt(scanner.nextLine());
            if (jumlahTiket <= 0) {
                System.out.println("Jumlah tiket harus lebih dari 0.");
                return;
            }

            Pemesanan pemesanan = new Pemesanan(pelanggan, tiket, jumlahTiket);

            System.out.println("\n=== Detail Pemesanan ===");
            System.out.println(pemesanan);

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan input. Pastikan memasukkan data dengan benar.");
        } finally {
            scanner.close();
        }
    }
}
