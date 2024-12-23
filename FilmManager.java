import java.util.*;
import java.sql.*;

public class FilmManager {
  public static void main(String[] args) {
    // Menggunakan try-with-resources untuk memastikan Scanner ditutup dengan benar
    try (Scanner scanner = new Scanner(System.in)) {

      // Login system
      System.out.println("=== Login System ===");

      // Membuat captcha sederhana
      Random random = new Random();
      int captchaNumber1 = random.nextInt(10); // Angka pertama
      int captchaNumber2 = random.nextInt(10); // Angka kedua
      int captchaResult = captchaNumber1 + captchaNumber2; // Hasil captcha

      System.out.print("Username: ");
      String username = scanner.nextLine();
      System.out.print("Password: ");
      String password = scanner.nextLine();

      // Tampilkan captcha
      System.out.println("Untuk melanjutkan, masukkan hasil dari operasi berikut:");
      System.out.println(captchaNumber1 + " + " + captchaNumber2 + " = ?");
      System.out.print("Masukkan hasil: ");

      while (!scanner.hasNextInt()) { // Validasi input captcha
        System.out.println("Input tidak valid. Masukkan angka.");
        scanner.next();
      }
      int userCaptcha = scanner.nextInt();

      // Validasi login dan captcha
      if (!username.equals("admin") || !password.equals("admin")) {
        System.out.println("Login failed. Invalid username atau password.");
        return;
      } else if (userCaptcha != captchaResult) {
        System.out.println("Login failed. Captcha tidak sesuai.");
        return;
      }

      System.out.println("Login Berhasil. Welcome, admin!");

      try {
        //JDBC Databae dengan MySQL
        FilmDatabase db = new FilmDatabase("jdbc:mysql://localhost:3306/filmlayarlebar", "root", "admin");
        boolean exit = false;

        while (!exit) { //Looping dengan menggunakan While
          System.out.println("\n=== Menu Pencatatan Film ===");
          System.out.println("1. Tambahkan Film");
          System.out.println("2. Lihat Semua Film");
          System.out.println("3. Update Film");
          System.out.println("4. Hapus Film");
          System.out.println("5. Exit");
          System.out.print("Pilih Menu Angka: ");

          while (!scanner.hasNextInt()) { // Validasi Input 
            System.out.println("Invalid input. Tolong Masukkan Angka.");
            scanner.next();
          }
          int choice = scanner.nextInt();
          scanner.nextLine(); // consume newline

          switch (choice) {
          case 1: {
            System.out.println("Pilih jenis film:");
            System.out.println("1. Film Animasi");
            System.out.println("2. Film Live Action");
            System.out.print("Masukkan pilihan: ");

            int jenisFilm = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Masukkan Judul: ");
            //Manipulasi String            
            String judul = scanner.nextLine().trim(); // Manipulasi String - Trim - Menghapus spasi berlebih
            judul = judul.toUpperCase(); // Manipulasi String - Ubah ke huruf kapital
            System.out.print("Masukkan Tahun Rilis: ");
            int tahunRilis = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Masukkan Genre: ");
            String genre = scanner.nextLine();
            System.out.print("Masukkan Durasi (dalam menit): ");
            int durasi = scanner.nextInt();
            System.out.print("Masukkan Rating (dari 10, contoh=9,2): ");
            double rating = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            //Percabangan If-Else
            if (jenisFilm == 1) { // Film Animasi
              System.out.print("Masukkan Style Animasi (contoh: 2D, 3D): ");
              String gayaAnimasi = scanner.nextLine();

              FilmAnimasi filmAnimasi = new FilmAnimasi(0, judul, tahunRilis, genre, durasi, rating, gayaAnimasi);
              db.addFilm(filmAnimasi); // Menambahkan film ke database
              System.out.println("Film Animasi berhasil ditambahkan.");
            } else if (jenisFilm == 2) { // Film Live Action
              System.out.print("Masukkan Jenis Action (contoh: Tembak-menembak, Pertarungan Tangan Kosong): ");
              String jenisAction = scanner.nextLine();

              FilmLiveAction filmLiveAction = new FilmLiveAction(0, judul, tahunRilis, genre, durasi, rating, jenisAction);
              db.addFilm(filmLiveAction); // Menambahkan film ke database
              System.out.println("Film Live Action berhasil ditambahkan.");
            } else {
              System.out.println("Pilihan jenis film tidak valid.");
            }
            break;
          }
          case 2: {
            //Collection Framework - ArrayList
            ArrayList < Film > films = db.getAllFilms();
            //Looping - For
            for (Film film: films) {
              System.out.println("====================");
              film.displayFilmDetails();
              System.out.println("====================");
            }
            break;
          }
          case 3: {
            System.out.print("Masukkan ID Film: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Masukkan Judul Baru: ");
            String newTitle = scanner.nextLine();
            System.out.print("Masukkan Rating Baru: ");
            double newRating = scanner.nextDouble();

            db.updateFilm(id, newTitle, newRating);
            System.out.println("Berhasil Mengupdate Film.");
            break;
          }
          case 4: {
            System.out.print("Masukkan ID Film untuk Dihapus: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println("Apakah Anda yakin ingin menghapus film ini? (Y/N)");
            String confirmation = scanner.nextLine().trim().toUpperCase();

            if (confirmation.equals("Y")) {
              db.deleteFilm(id);
              System.out.println("Film Berhasil Dihapus!");
            } else if (confirmation.equals("N")) {
              System.out.println("Penghapusan dibatalkan.");
            } else {
              System.out.println("Pilihan tidak valid. Penghapusan dibatalkan.");
            }
            break;
          }
          case 5:
            exit = true;
            break;

          default:
            System.out.println("Invalid option. Please try again.");
            break;
          }

        }
      } catch (SQLException e) { //ExceptionHandling
        e.printStackTrace();
      }
    }
  }
}