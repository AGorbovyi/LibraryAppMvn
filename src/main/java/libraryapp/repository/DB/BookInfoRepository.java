package libraryapp.repository.DB;

import libraryapp.entity.BookInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class BookInfoRepository implements CrudRepository<Integer, BookInfo> {
    private String java_library_db;
    private final String SQL_CREATE_BOOK_INFO_TABLE = "CREATE TABLE IF NOT EXISTS book_info (" +
            "    book_info_id       INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    is_in_library      BOOLEAN NOT NULL," +
            "    borrowed_to        INTEGER," +
            "    borrowed_date      DATE," +
            "    borrowed_duration  INTEGER," +
            "    return_date        DATE )";
    private final String SQL_DELETE_BOOK_INFO_TABLE = "DELETE FROM book_info";
    private final String SQL_INSERT_BOOK_INFO = "INSERT INTO book_info (is_in_library, borrowed_to, borrowed_date, borrowed_duration, return_date) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_UPDATE_BOOK_INFO = "UPDATE book_info SET is_in_library = ?, borrowed_to = ?, borrowed_date = ?, borrowed_duration = ?, return_date = ? WHERE id = ?";
    private final String SQL_FIND_BOOK_INFO_BY_ID = "SELECT * FROM book_info WHERE id = ?";
    private final String SQL_FIND_ALL_BOOK_INFO = "SELECT * FROM book_info";
    private final String SQL_DELETE_BOOK_INFO_BY_ID = "DELETE FROM book_info WHERE id = ?";

    public BookInfoRepository(String java_library_db) {
        this.java_library_db = java_library_db;
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL_CREATE_BOOK_INFO_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(BookInfo value) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT_BOOK_INFO, Statement.RETURN_GENERATED_KEYS)) {
            psi.setBoolean(1, value.isInLibrary());
            psi.setInt(2, value.getBorrowedTo());
            psi.setDate(3, value.getBorrowedDate() != null ? Date.valueOf(value.getBorrowedDate()) : null);
            psi.setInt(4, value.getBorrowedDuration());
            psi.setDate(5, value.getReturnDate() != null ? Date.valueOf(value.getReturnDate()) : null);
            psi.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookInfo get(Integer id) {
        BookInfo bookInfo = null;
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BOOK_INFO_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bookInfo = new BookInfo();
                bookInfo.setInLibrary(rs.getBoolean("isInLibrary"));
                bookInfo.setBorrowedTo(rs.getInt("borrowedTo"));
                bookInfo.setBorrowedDate(rs.getDate("borrowedDate") != null ? rs.getDate("borrowedDate").toLocalDate() : null);
                bookInfo.setBorrowedDuration(rs.getInt("borrowedDuration"));
                bookInfo.setReturnDate(rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookInfo;
    }

    @Override
    public boolean remove(Integer id) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BOOK_INFO_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Collection<BookInfo> findAll() {
        Collection<BookInfo> bookInfos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_BOOK_INFO);
            while (rs.next()) {
                BookInfo bookInfo = new BookInfo();
                bookInfo.setInLibrary(rs.getBoolean("isInLibrary"));
                bookInfo.setBorrowedTo(rs.getInt("borrowedTo"));
                bookInfo.setBorrowedDate(rs.getDate("borrowedDate").toLocalDate());
                bookInfo.setBorrowedDuration(rs.getInt("borrowedDuration"));
                bookInfo.setReturnDate(rs.getDate("returnDate").toLocalDate());
                bookInfos.add(bookInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookInfos;
    }

    @Override
    public void init() {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement ps = connection.prepareStatement(SQL_INSERT_BOOK_INFO)) {
            ps.setBoolean(1, true);  // isInLibrary
            ps.setString(2, null);   // borrowedTo
            ps.setDate(3, null);     // borrowedDate
            ps.setInt(4, 0);         // borrowedDuration
            ps.setDate(5, null);     // returnDate
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL_DELETE_BOOK_INFO_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Integer id, BookInfo newValue) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BOOK_INFO)) {
            ps.setBoolean(1, newValue.isInLibrary());
            ps.setInt(2, newValue.getBorrowedTo());
            ps.setDate(3, newValue.getBorrowedDate() != null ? Date.valueOf(newValue.getBorrowedDate()) : null);
            ps.setInt(4, newValue.getBorrowedDuration());
            ps.setDate(5, newValue.getReturnDate() != null ? Date.valueOf(newValue.getReturnDate()) : null);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}