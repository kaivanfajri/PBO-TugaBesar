// Parent class
import java.time.LocalDate;

class Film implements FilmManagement {
    protected int idFilm;
    protected String judul;
    protected int tahunRilis;
    protected String genre;
    protected int durasi; // in minutes
    protected double rating; // scale of 10

    public Film(int idFilm, String judul, int tahunRilis, String genre, int durasi, double rating) {
        this.idFilm = idFilm;
        this.judul = judul;
        this.tahunRilis = tahunRilis;
        this.genre = genre;
        this.durasi = durasi;
        this.rating = rating;
    }

    @Override
    public void displayFilmDetails() {
        System.out.println("ID Film: " + idFilm);
        System.out.println("Judul: " + judul.toUpperCase());//Manipulasi String
        System.out.println("Tahun Rilis: " + tahunRilis);
        System.out.println("Genre: " + genre);
        System.out.println("Durasi: " + durasi + " menit");
        System.out.println("Rating: " + rating);

        // Menghitung jarak tahun rilis dengan tahun saat ini (Manipulasi Date)
        int currentYear = LocalDate.now().getYear();
        int jarakTahun = currentYear - tahunRilis; // Perhitungan Matematika
        System.out.println("Lama Film Setelah Dirilis: " + jarakTahun + " tahun");
    }

    @Override
    public double calculateRatingPercentage() {
        return (rating / 10) * 100;
    }
}
