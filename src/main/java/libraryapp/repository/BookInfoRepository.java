package libraryapp.repository;

import libraryapp.entity.BookInfo;

import java.sql.*;
import java.util.Collection;

public class BookInfoRepository implements CrudRepository<Integer, BookInfo> {
    private String java_library_db;
    private final String SQL_CREATE_BOOK_INFO_TABLE = "CREATE TABLE IF NOT EXISTS book_info (" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "    isInLibrary BOOLEAN NOT NULL," +
            "    borrowedTo TEXT," +
            "    borrowedDate DATE," +
            "    borrowedDuration INTEGER," +
            "    returnDate DATE" +
            ")";
    private final String SQL_DELETE_BOOK_INFO_TABLE = "DELETE FROM book_info";
    private final String SQL_INSERT_BOOK_INFO = "INSERT INTO book_info (isInLibrary, borrowedTo, borrowedDate, borrowedDuration, returnDate) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_UPDATE_BOOK_INFO = "UPDATE book_info SET isInLibrary = ?, borrowedTo = ?, borrowedDate = ?, borrowedDuration = ?, returnDate = ? WHERE id = ?";
    private final String SQL_FIND_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    private final String SQL_FIND_BOOK_INFO_BY_ID = "SELECT * FROM book_info WHERE id = ?";

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

            ResultSet rs = psi.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void get(Integer id) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public Collection<BookInfo> findAll() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void deleteAll() {

    }
}
