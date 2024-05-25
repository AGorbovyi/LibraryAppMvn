package libraryapp.repository.db;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Larysa Petrova
 * @version 21-Apr-24
 **/

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
    private String javaLibraryDb; //база данных
    private final String SQL_CREATE_TABLE = "CREATE TABLE user_card (" +
            "    user_card_id   INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    books_limit    INTEGER NOT NULL," +
            "    is_closed      BOOLEAN NOT NULL," +
            "    user_id        INTEGER NOT NULL," +
            "    FOREIGN KEY (user_id) " +
            "       REFERENCES user (user_id) " +
            "           ON DELETE CASCADE " +
            "           ON UPDATE NO ACTION )";
    private final String SQL_INSERT = "INSERT INTO user_card (books_limit,is_closed) VALUES (?,?,?) ";
    private final String SQL_UPDATE = "UPDATE user_card SET books_limit = ?,is_closed = ? WHERE user_card_id = ? ";
    private final String SQL_GET = "SELECT * FROM user_card WHERE user_card_id  = ? ";
    private final String SQL_DELETE_BY_ID = "DELETE FROM user_card WHERE user_card_id =? ";
    private final String SQL_FIND_ALL = "SELECT * FROM user_card ";

    public UserCardRepository() {
    }

    public UserCardRepository(String javaLibraryDb) {
        this.javaLibraryDb = javaLibraryDb;
    }

    @Override
    public void save(UserCard userCard) {
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             PreparedStatement psi = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psu = connection.prepareStatement(SQL_UPDATE)) {
            if (userCard.getUserId() == null) {
                //insert record
                psi.setInt(1, userCard.getUser().getId());
                psi.setInt(2, userCard.getBooksLimit());
                psi.setBoolean(3, userCard.isClosed());
                psi.executeUpdate();

                ResultSet rs = psi.getGeneratedKeys();
                if (rs.next()) {
                    userCard.setId(rs.getInt(1));
                }
            } else {
                //update record
                psu.setInt(1, userCard.getUser().getId());
                psu.setInt(2, userCard.getBooksLimit());
                psu.setBoolean(3, userCard.isClosed());
                psu.setInt(4, userCard.getId());
                psi.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserCard findById(Integer id) {
        UserCard userCard = null;
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             PreparedStatement ps = connection.prepareStatement(SQL_GET)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userCard = new UserCard(
                        rs.getInt("user_id"),
                        rs.getInt("books_limit"),
                        rs.getBoolean("is_closed"));
            }
            return userCard;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void remove(Integer id) {
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
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
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL);
            while (rs.next()) {
                userCard.add(new UserCard(
                        rs.getInt("user_id"),
                        rs.getInt("books_limit"),
                        rs.getBoolean("is_closed")));
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
        for (UserCard user : usersList) {
            save(user);
        }
    }
}