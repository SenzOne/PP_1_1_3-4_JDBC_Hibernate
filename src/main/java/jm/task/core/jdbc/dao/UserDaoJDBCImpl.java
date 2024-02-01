package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {//todo: connection - точка доступа и отдельное поле, инициализация через конструктор

    }

    private final static String CREATE_USERS_QUERY =
            "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25), lastName VARCHAR(25), age SMALLINT)";

    public void createUsersTable() {
        try (var connection = Util.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_USERS_QUERY);
        } catch (SQLException e) {
            //todo:
            throw new RuntimeException("...роняем приложение, если дальнейшая работа не целесообразна: в ином случае - stackTrace " + e.getMessage());
        }
    }

    private final static String DROP_USERS_TABLE_QUERY = "DROP TABLE IF EXISTS users";

    public void dropUsersTable() {
        try (var connection = Util.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_USERS_TABLE_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final static String INSERT_IN_TO_USERS_QUERY = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

    public void saveUser(String name, String lastName, byte age) {
        try (var connection = Util.getConnectionFromPool();
             var preparedStatement = connection.prepareStatement(INSERT_IN_TO_USERS_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final static String DELETE_USERS_BY_ID_QUERY = "DELETE FROM users WHERE id=?;";

    public void removeUserById(long id) {
        try (var connection = Util.getConnectionFromPool();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM users;";

    public List<User> getAllUsers() {
        List<User> userFromDb = new ArrayList<>();
        try (var connection = Util.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            var executeResult = statement.executeQuery(GET_ALL_USERS_QUERY);
            while (executeResult.next()) {
                User user = new User();
                user.setId((long) executeResult.getInt("id"));
                user.setName(executeResult.getObject("name", String.class));
                user.setLastName(executeResult.getObject("lastname", String.class));
                user.setAge(executeResult.getObject("age", Byte.class));
                userFromDb.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userFromDb;
    }

    private final static String CLEAN_USERS_TABLE_QUERY = "TRUNCATE TABLE users";

    public void cleanUsersTable() {
        try (var connection = Util.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            statement.execute(CLEAN_USERS_TABLE_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
