public class Menu {
    private final String nama;
    private final double harga;
    private int stok;

    public Menu(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public boolean isOutOfStock() {
        return stok <= 0;
    }

    public void kurangiStok(int jumlah) {
        if (jumlah > 0 && jumlah <= stok) {
            stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi untuk " + nama);
        }
    }

    public String toString() {
        return nama + " [" + stok + "]\tRp. " + harga;
    }
}
