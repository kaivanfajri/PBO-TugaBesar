// Operasi Database
import java.util.*;
import java.sql.*;

class FilmDatabase {
  private Connection connection;

  public FilmDatabase(String dbURL, String user, String password) throws SQLException {
    connection = DriverManager.getConnection(dbURL, user, password);
  }

  // Create
  public void addFilm(Film film) throws SQLException {
    String sql = "INSERT INTO film (judul, tahun_rilis, genre, durasi, rating) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, film.judul);
      statement.setInt(2, film.tahunRilis);
      statement.setString(3, film.genre);
      statement.setInt(4, film.durasi);
      statement.setDouble(5, film.rating);
      statement.executeUpdate();

      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int generatedId = generatedKeys.getInt(1);
          System.out.println("Film added with ID: " + generatedId);
        }
      }
    }
  }

  // Read
  public ArrayList < Film > getAllFilms() throws SQLException {
    ArrayList < Film > filmList = new ArrayList < > ();
    String sql = "SELECT * FROM film";
    try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
      while (rs.next()) {
        int idFilm = rs.getInt("id_film");
        String judul = rs.getString("judul");
        int tahunRilis = rs.getInt("tahun_rilis");
        String genre = rs.getString("genre");
        int durasi = rs.getInt("durasi");
        double rating = rs.getDouble("rating");
        filmList.add(new Film(idFilm, judul, tahunRilis, genre, durasi, rating));
      }
    }
    return filmList;
  }

  // Update
  public void updateFilm(int idFilm, String newTitle, double newRating) throws SQLException {
    String sql = "UPDATE film SET judul = ?, rating = ? WHERE id_film = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, newTitle);
      statement.setDouble(2, newRating);
      statement.setInt(3, idFilm);
      statement.executeUpdate();
    }
  }

  // Delete
  public void deleteFilm(int idFilm) throws SQLException {
    String sql = "DELETE FROM film WHERE id_film = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, idFilm);
      statement.executeUpdate();
    }
  }

}