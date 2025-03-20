public class Tiket {
    private final Film film;
    private final String jadwal;

    public Tiket(Film film, String jadwal){
        this.film = film;
        this.jadwal = jadwal;
    }

    public Film getFilm() {
        return film;
    }

    public String getJadwal() {
        return jadwal;
    }
    
    public String toString() {
        return "Tiket untuk film: " + film.getJudul() + " | Jadwal: " + jadwal;
    }
}
