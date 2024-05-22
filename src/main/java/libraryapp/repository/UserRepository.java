package libraryapp.repository;

import libraryapp.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserRepository implements CrudRepository<Integer, User> {
    private String javaLibraryDb;
    private final String SQL_CREATE_TABLE = "CREATE TABLE user (" +
            "    id         INTEGER NOT NULL," +
            "    name       TEXT  NOT NULL," +
            "    surname    TEXT NOT NULL )";
    private final String SQL_INSERT_USER = "INSERT INTO user (id,name,surname) VALUES (?,?,?) ";
    private final String SQL_UPDATE_USER = "UPDATE user SET id=?,name=?,surname=? WHERE id = ? ";
    private final String SQL_FINDBYID_USER = "SELECT * FROM user WHERE id = ? ";
    private final String SQL_DELETE_BY_ID_USER = "DELETE FROM user WHERE id =? ";
    private final String SQL_FIND_ALL_USER = "SELECT * FROM user ";

    public UserRepository(String javaLibraryDb) {
        this.javaLibraryDb = javaLibraryDb;
    }

    @Override
    public void save(User user) {
        if (user.getId() != null && findById(user.getId()) != null) {
            try (Connection connection = DriverManager.getConnection(javaLibraryDb);
                 PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_USER)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setInt(3, user.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Connection connection = DriverManager.getConnection(javaLibraryDb);
                 PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
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
                        rs.getString("name"),
                        rs.getString("surname"));
                user.setId(rs.getInt("id"));
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
