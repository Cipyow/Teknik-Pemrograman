public class Pelanggan {
    private String nama;
    private String noHP;

    public Pelanggan(String nama, String noHP) {
        this.nama = nama;
        this.noHP = noHP;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return noHP;
    }

    public String toString() {
        return "Pelanggan: " + nama + " | No HP: " + noHP;
    }
}
