// Subclass FilmAnimasi
class FilmAnimasi extends Film {
    private String gayaAnimasi;

    public FilmAnimasi(int idFilm, String judul, int tahunRilis, String genre, int durasi, double rating, String gayaAnimasi) {
        super(idFilm, judul, tahunRilis, genre, durasi, rating);
        this.gayaAnimasi = gayaAnimasi;
    }

    @Override
    public void displayFilmDetails() {
        super.displayFilmDetails();
        System.out.println("Gaya Animasi: " + gayaAnimasi);
    }
}
