package libraryapp.repository;
/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

import libraryapp.entity.Book;
import libraryapp.entity.User;
import libraryapp.entity.UserCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserCardRepository implements CrudRepository<Integer, UserCard> {
    // private final Map<Integer, UserCard> userCards;
    private String java_library_db; //база данных
    private final String SQL_CREATE_TABLE = "CREATE TABLE userCard (" +
            "    id            INTEGER NOT NULL," +
            "    user          TEXT    NOT NULL," +
            "    borrowedBooks TEXT    NOT NULL," +

            "    booksLimit    INTEGER NOT NULL," +
            "    isClosed      BOOLEAN NOT NULL)";
    private final String SQL_INSERT = "INSERT INTO userCard (user,borrowedBooks,booksLimit,isClosed,maxBooksLimit) VALUES (?,?,?,?,?) ";
    private final String SQL_UPDATE = "UPDATE userCard SET user=?,borrowedBooks=?,booksLimit=?,isClosed=?,maxBooksLimit=? WHERE id = ? ";
    private final String SQL_GET = "SELECT * FROM userCard WHERE id = ? ";
    private final String SQL_DELETE_BY_ID = "DELETE FROM userCard WHERE id =? ";
    private final String SQL_FIND_ALL = "SELECT * FROM userCard ";

    public UserCardRepository(String java_library_db) {
        this.java_library_db = java_library_db;
    }

    public UserCardRepository() {
        // this.userCards = new HashMap<>();
    }

    @Override
    public void save(UserCard userCard) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE)) {
            if (userCard.getUserId() == null) {
                //insert record
                psi.setObject(1, userCard.getUser());
                psi.setObject(2, userCard.getUserBorrowedBooks());
                psi.setInt(3, userCard.getBooksLimit());
                psi.setBoolean(4, userCard.isClosed());
                psi.setInt(5, userCard.getMaxBooksLimit());
                psi.executeUpdate();

                ResultSet rs = psi.getGeneratedKeys();
                if (rs.next()) {
                    userCard.setId(rs.getInt(1));
                }
            } else {
                //update record
                psu.setObject(1, userCard.getUser());
                psu.setObject(2, userCard.getUserBorrowedBooks());
                psu.setInt(3, userCard.getBooksLimit());
                psu.setBoolean(4, userCard.isClosed());
                psi.setInt(5, userCard.getMaxBooksLimit());
                psu.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserCard get(Integer id) {
        UserCard userCard = null;
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement ps = connection.prepareStatement(SQL_GET)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userCard = new UserCard(
                        rs.getInt("user_id"),
                        (User) rs.getObject("user"),
                        (List<Book>) rs.getObject("borrowedBooks"),
                        rs.getInt("booksLimit"),
                        rs.getBoolean("isClosed"));
            }
            return userCard;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void remove(Integer id) {
        try (Connection connection = DriverManager.getConnection(java_library_db);
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<UserCard> findAll() {
        Collection<UserCard> userCard = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(java_library_db);
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL);
            while (rs.next()) {
                userCard.add(new UserCard(
                        rs.getInt("userId"),
                        (User) rs.getObject("user"),
                        (List<Book>) rs.getObject("borrowedBooks"),
                        rs.getInt("booksLimit"),
                        rs.getBoolean("isClosed")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userCard;
    }

    @Override
    public void deleteAll() {

    }

    public void init() {
        List<User> usersList = new ArrayList<>(List.of(
                new User("Anton", "Gorbovyi"),
                new User("Halyna", "Potyvaieve"),
                new User("Yaroslav", "Boiko"),
                new User("Larysa", "Petrova"),
                new User("Andrey", "Shishkov")
        ));
        for (User user : usersList) {
            UserCard card = new UserCard(rs.getInt("userId"), user, (List<Book>) rs.getObject("borrowedBooks"), rs.getInt("booksLimit"), rs.getBoolean("isClosed"));
            this.save(card);
        }
    }


}






