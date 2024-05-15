package libraryapp.repository;

import libraryapp.entity.Book;
import libraryapp.entity.BookInfo;

import java.sql.*;
import java.util.*;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */

public class BookCatalogRepository implements CrudRepository<Integer, Book> {
    private String java_library_db;
    private final String SQL_CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS book (" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "    author TEXT NOT NULL," +
            "    bookTitle TEXT NOT NULL," +
            "    genre TEXT," +
            "    publisher TEXT" +
            ")";
    private BookInfoRepository bookInfoRepository;

    private final String SQL_DELETE_BOOK_TABLE = "DELETE FROM book";
    private final String SQL_DELETE_BOOK_INFO_TABLE = "DELETE FROM book_info";
    private final String SQL_INSERT_BOOK = "INSERT INTO book (author, bookTitle, genre, publisher) VALUES (?, ?, ?, ?)";
    private final String SQL_INSERT_BOOK_INFO = "INSERT INTO book_info (id, isInLibrary, borrowedTo, borrowedDate, borrowedDuration, returnDate) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE_BOOK = "UPDATE book SET author = ?, bookTitle = ?, genre = ?, publisher = ? WHERE id = ?";
    private final String SQL_UPDATE_BOOK_INFO = "UPDATE book_info SET isInLibrary = ?, borrowedTo = ?, borrowedDate = ?, borrowedDuration = ?, returnDate = ? WHERE id = ?";
    private final String SQL_FIND_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    private final String SQL_FIND_BOOK_INFO_BY_ID = "SELECT * FROM book_info WHERE id = ?";
    private final String SQL_FIND_ALL_BOOKS = "SELECT * FROM book";
    private final String SQL_DELETE_BOOK_BY_ID = "DELETE FROM book WHERE id = ?";
    private final String SQL_DELETE_BOOK_INFO_BY_ID = "DELETE FROM book_info WHERE id = ?";

    public BookCatalogRepository() {
    }

    public BookCatalogRepository(String java_library_db) {
        this.java_library_db = java_library_db;
        this.bookInfoRepository = new BookInfoRepository(java_library_db);
        init();
    }

    @Override
    public void save(Book book) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement psiBook = connection.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psiBookInfo = connection.prepareStatement(SQL_INSERT_BOOK_INFO)) {
            if (book.getId() == null) {
                // insert book
                psiBook.setString(1, book.getAuthor());
                psiBook.setString(2, book.getBookTitle());
                psiBook.setString(3, book.getGenre());
                psiBook.setString(4, book.getPublisher());
                psiBook.executeUpdate();

                ResultSet rs = psiBook.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    book.setId(generatedId);
                    book.getBookInfo().setId(generatedId);
                }

                psiBookInfo.setInt(1, book.getId());
                psiBookInfo.setBoolean(2, book.getBookInfo().isInLibrary());
                psiBookInfo.setObject(3, book.getBookInfo().getBorrowedTo(), Types.INTEGER);
                psiBookInfo.setObject(4, book.getBookInfo().getBorrowedDate(), Types.DATE);
                psiBookInfo.setObject(5, book.getBookInfo().getBorrowedDuration(), Types.INTEGER);
                psiBookInfo.setObject(6, book.getBookInfo().getReturnDate(), Types.DATE);
                psiBookInfo.executeUpdate();
            } else {
                try (PreparedStatement psuBook = connection.prepareStatement(SQL_UPDATE_BOOK);
                     PreparedStatement psuBookInfo = connection.prepareStatement(SQL_UPDATE_BOOK_INFO)) {
                    psuBook.setString(1, book.getAuthor());
                    psuBook.setString(2, book.getBookTitle());
                    psuBook.setString(3, book.getGenre());
                    psuBook.setString(4, book.getPublisher());
                    psuBook.setInt(5, book.getId());
                    psuBook.executeUpdate();

                    psuBookInfo.setBoolean(1, book.getBookInfo().isInLibrary());
                    psuBookInfo.setObject(2, book.getBookInfo().getBorrowedTo(), Types.INTEGER);
                    psuBookInfo.setObject(3, book.getBookInfo().getBorrowedDate(), Types.DATE);
                    psuBookInfo.setObject(4, book.getBookInfo().getBorrowedDuration(), Types.INTEGER);
                    psuBookInfo.setObject(5, book.getBookInfo().getReturnDate(), Types.DATE);
                    psuBookInfo.setInt(6, book.getId());
                    psuBookInfo.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book get(Integer id) {
        Book book = null;
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement psBook = connection.prepareStatement(SQL_FIND_BOOK_BY_ID);
             PreparedStatement psBookInfo = connection.prepareStatement(SQL_FIND_BOOK_INFO_BY_ID)) {
            psBook.setInt(1, id);
            ResultSet rsBook = psBook.executeQuery();
            if (rsBook.next()) {
                book = new Book(
                        rsBook.getInt("id"),
                        rsBook.getString("author"),
                        rsBook.getString("bookTitle"),
                        rsBook.getString("genre"),
                        rsBook.getString("publisher"),
                        null); // We'll set bookInfo later
                BookInfo bookInfo = new BookInfo();
                psBookInfo.setInt(1, id);
                ResultSet rsBookInfo = psBookInfo.executeQuery();
                if (rsBookInfo.next()) {
                    bookInfo.setId(rsBookInfo.getInt("id"));
                    bookInfo.setInLibrary(rsBookInfo.getBoolean("isInLibrary"));
                    bookInfo.setBorrowedTo(rsBookInfo.getInt("borrowedTo"));
                    bookInfo.setBorrowedDate(rsBookInfo.getDate("borrowedDate").toLocalDate());
                    bookInfo.setBorrowedDuration(rsBookInfo.getInt("borrowedDuration"));
                    bookInfo.setReturnDate(rsBookInfo.getDate("returnDate").toLocalDate());
                    book.setBookInfo(bookInfo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public boolean remove(Integer id) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement psBook = connection.prepareStatement(SQL_DELETE_BOOK_BY_ID);
             PreparedStatement psBookInfo = connection.prepareStatement(SQL_DELETE_BOOK_INFO_BY_ID)) {
            psBook.setInt(1, id);
            psBook.executeUpdate();

            psBookInfo.setInt(1, id);
            psBookInfo.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Collection<Book> findAll() {
        Collection<Book> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmtBook = connection.createStatement();
             Statement stmtBookInfo = connection.createStatement()) {
            ResultSet rsBook = stmtBook.executeQuery(SQL_FIND_ALL_BOOKS);
            while (rsBook.next()) {
                Book book = new Book(
                        rsBook.getInt("id"),
                        rsBook.getString("author"),
                        rsBook.getString("bookTitle"),
                        rsBook.getString("genre"),
                        rsBook.getString("publisher"),
                        null); // We'll set bookInfo later

                BookInfo bookInfo = new BookInfo();
                try (PreparedStatement psBookInfo = connection.prepareStatement(SQL_FIND_BOOK_INFO_BY_ID)) {
                    psBookInfo.setInt(1, book.getId());
                    ResultSet rsBookInfo = psBookInfo.executeQuery();
                    if (rsBookInfo.next()) {
                        bookInfo.setId(rsBookInfo.getInt("id"));
                        bookInfo.setInLibrary(rsBookInfo.getBoolean("isInLibrary"));
                        bookInfo.setBorrowedTo(rsBookInfo.getInt("borrowedTo"));
                        bookInfo.setBorrowedDate(rsBookInfo.getDate("borrowedDate").toLocalDate());
                        bookInfo.setBorrowedDuration(rsBookInfo.getInt("borrowedDuration"));
                        bookInfo.setReturnDate(rsBookInfo.getDate("returnDate").toLocalDate());
                        book.setBookInfo(bookInfo);
                    }
                }
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL_DELETE_BOOK_TABLE);
            stmt.executeUpdate(SQL_DELETE_BOOK_INFO_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void init() {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL_CREATE_BOOK_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bookInfoRepository.init();
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE author LIKE ?";

        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + author + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("author"),
                        resultSet.getString("bookTitle"),
                        resultSet.getString("genre"),
                        resultSet.getString("publisher"),
                        null
                );
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE bookTitle LIKE ?";

        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + title + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("author"),
                        resultSet.getString("bookTitle"),
                        resultSet.getString("genre"),
                        resultSet.getString("publisher"),
                        null
                );
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

}
