// Subclass FilmLiveAction
class FilmLiveAction extends Film {
    private String jenisAction;

    public FilmLiveAction(int idFilm, String judul, int tahunRilis, String genre, int durasi, double rating, String jenisAction) {
        super(idFilm, judul, tahunRilis, genre, durasi, rating);
        this.jenisAction = jenisAction;
    }

    @Override
    public void displayFilmDetails() {
        super.displayFilmDetails();
        System.out.println("Jenis Action: " + jenisAction);
    }
}
