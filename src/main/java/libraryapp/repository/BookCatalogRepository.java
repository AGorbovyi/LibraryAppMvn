package libraryapp.repository;

import libraryapp.entity.Book;
import libraryapp.entity.BookInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class BookCatalogRepository implements CrudRepository<Integer, Book> {
    private CrudRepository<Integer, BookInfo>  BookInfoRepository;
    private String java_library_db;
    private final String SQL_CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS book (" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "    author TEXT NOT NULL," +
            "    bookTitle TEXT NOT NULL," +
            "    genre TEXT," +
            "    publisher TEXT," +
            "    bookInfoId INTEGER," +
            "    FOREIGN KEY (bookInfoId) REFERENCES book_info(id)" +
            ")";

    private final String SQL_DELETE_BOOK_TABLE = "DELETE FROM book";
    private final String SQL_INSERT_BOOK = "INSERT INTO book (author, bookTitle, genre, publisher, bookInfoId) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_UPDATE_BOOK = "UPDATE book SET author = ?, bookTitle = ?, genre = ?, publisher = ?, bookInfoId = ? WHERE id = ?";
    private final String SQL_FIND_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    private final String SQL_FIND_ALL_BOOKS = "SELECT * FROM book";
    private final String SQL_DELETE_BOOK_BY_ID = "DELETE FROM book WHERE id = ?";



    public BookCatalogRepository(String java_library_db) {
        this.java_library_db = java_library_db;
    }

    @Override
    public void save(Book book) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE_BOOK)) {
            if (book.getId() == null) {
                // insert record
                psi.setString(1, book.getAuthor());
                psi.setString(2, book.getBookTitle());
                psi.setString(3, book.getGenre());
                psi.setString(4, book.getPublisher());
                psi.setObject(5, book.getBookInfo());
                psi.executeUpdate();

                ResultSet rs = psi.getGeneratedKeys();
                if (rs.next()) {
                    book.setId(rs.getInt(1));
                }
            } else {
                // update record
                psu.setString(1, book.getAuthor());
                psu.setString(2, book.getBookTitle());
                psu.setString(3, book.getGenre());
                psu.setString(4, book.getPublisher());
                psu.setObject(5, book.getBookInfo());
                psu.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Book get(Integer id) {
        Book book = null;
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BOOK_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = new Book(
                        rs.getInt("id"),
                        rs.getString("author"),
                        rs.getString("bookTitle"),
                        rs.getString("genre"),
                        rs.getString("publisher"),
                        rs.getBoolean("bookInfo"));

            }
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Integer id) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BOOK_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Book> findAll() {
        Collection<Book> pizzas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_BOOKS);
            while (rs.next()) {
                pizzas.add(new Book(
                        rs.getInt("id"),
                        rs.getString("author"),
                        rs.getString("bookTitle"),
                        rs.getString("genre"),
                        rs.getString("publisher"),
                        rs.getObject("bookInfo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pizzas;

    }
    @Override
    public void deleteAll() {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL_CREATE_BOOK_TABLE);
            stmt.executeUpdate(SQL_DELETE_BOOK_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
        List<Book> books = new ArrayList<>(List.of(
                new Book(null, "Taras Shevchenko", "Kobzar", "Poetry", "Kyiv Old Guard", ("bookInfo")),
                new Book(null, "Ivan Franko", "Za dvoma zaitsiamy", "Drama", "Lviv Printing House", ("bookInfo")),
                new Book(null, "Lesia Ukrainka", "Lisova pisnia", "Poetry", "Kyiv Old Guard", ("bookInfo")),
                new Book(null, "Ivan Nechuy-Levytsky", "Pіznorid", "Novel", "Kyiv Old Guard", ("bookInfo")),
                new Book(null, "Mykhailo Kotsiubynsky", "Intermezzo", "Novel", "Kyiv Old Guard", ("bookInfo")),
                new Book(null, "Panas Myrny", "Pisni smutku", "Novel", "Lviv Printing House", ("bookInfo")),
                new Book(null, "Ivan Franko", "Pisni smіlі", "Poetry", "Kyiv Old Guard", ("bookInfo")),
                new Book(null, "Lesia Ukrainka", "Kaminnyi hospodar", "Drama", "Lviv Printing House", ("bookInfo")),
                new Book(null, "Pavlo Tychyna", "Pluh", "Poetry", "Kyiv Old Guard", ("bookInfo"))
        ));
        books.forEach(this::save);
    }
}