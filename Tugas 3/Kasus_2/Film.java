import java.util.ArrayList;
import java.util.List;

public class Film {
    private String judul;
    private String genre;
    private int durasi;
    private double hargaTiket;
    private List<String> jadwalTayang;

    public Film(String judul, String genre, int durasi, double hargaTiket, List<String> jadwal){
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.hargaTiket = hargaTiket;
        this.jadwalTayang = new ArrayList<>(jadwal);
    }

    public String getJudul() {
        return judul;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public double getHarga() {
        return hargaTiket;
    }

    public List<String> getJadwalTayang() {
        return jadwalTayang;
    }

    public String toString(){
        return judul + " | Genre: " + genre + " | Durasi: " + durasi + " menit | Harga: " + hargaTiket;
    }
}
