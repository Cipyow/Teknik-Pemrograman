public class Pemesanan {
    private Pelanggan pelanggan;
    private Tiket tiket;
    private int jumlahTiket;

    public Pemesanan(Pelanggan pelanggan, Tiket tiket, int jumlahTiket) {
        this.pelanggan = pelanggan;
        this.tiket = tiket;
        this.jumlahTiket = jumlahTiket;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }

    public double hitungTotalHarga() {
        return jumlahTiket * tiket.getFilm().getHarga();
    }

    public String toString() {
        return pelanggan + "\n" + tiket + "\nJumlah Tiket: " + jumlahTiket + " | Total Harga: Rp. " + hitungTotalHarga();
    }
}
