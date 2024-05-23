package libraryapp.repository.db;

import libraryapp.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserRepository implements CrudRepository<Integer, User> {
    private String javaLibraryDb;
    private final String SQL_CREATE_TABLE = "CREATE TABLE user (" +
            "    user_id        INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    first_name     TEXT  NOT NULL," +
            "    last_name      TEXT NOT NULL )";
    private final String SQL_INSERT_USER = "INSERT INTO user (user_id,first_name,last_name) VALUES (?,?,?) ";
    private final String SQL_UPDATE_USER = "UPDATE user SET user_id=?,first_name=?,last_name=? WHERE id = ? ";
    private final String SQL_FINDBYID_USER = "SELECT * FROM user WHERE user_id = ? ";
    private final String SQL_DELETE_BY_ID_USER = "DELETE FROM user WHERE user_id =? ";
    private final String SQL_FIND_ALL_USER = "SELECT * FROM user ";

    public UserRepository() {
    }

    public UserRepository(String javaLibraryDb) {
        this.javaLibraryDb = javaLibraryDb;
    }

    @Override
    public void save(User user) {
        if (user.getUserId() != null && findById(user.getUserId()) != null) {
            try (Connection connection = DriverManager.getConnection(javaLibraryDb);
                 PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_USER)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setInt(3, user.getUserId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Connection connection = DriverManager.getConnection(javaLibraryDb);
                 PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    user.setUserId(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             PreparedStatement ps = connection.prepareStatement(SQL_FINDBYID_USER)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getString("first_name "),
                        rs.getString("last_name "));
                user.setUserId(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void remove(Integer id) {
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID_USER)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USER)) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("surname"));
                user.setId(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init() {
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             Statement stmt = connection.createStatement()) {
            stmt.execute(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
