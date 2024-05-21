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
        if (findById(user.getId()) != null) {
            try (Connection connection = DriverManager.getConnection(javaLibraryDb);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setInt(3, user.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Connection connection = DriverManager.getConnection(javaLibraryDb);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER)) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FINDBYID_USER)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User(
                user.setId(rs.getInt("id"),
                user.setName(rs.getString("name"),
                user.setSurname(rs.getString("surname"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void remove(Integer id) {
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID_USER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void init() {
    }


    @Override
    public void deleteAll() {
        try (Connection connection = DriverManager.getConnection(javaLibraryDb);
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
