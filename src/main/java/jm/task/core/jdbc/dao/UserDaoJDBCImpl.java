package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    //todo: codeStyle

    public UserDaoJDBCImpl() {//todo: connection - точка доступа и отдельное поле, инициализация через конструктор

    }

    //todo: выносим и правильно по смыслу именуем константв, например:
    private final static String CREATE_USERS_QUERY = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25), lastName VARCHAR(25), age SMALLINT)";

    public void createUsersTable() {
        try (var connection = Util.getConnection()) {//todo: в качестве ресурса - Statement (можно уедиться, он - AutoCloseable)
            Statement statement = connection.createStatement();
            boolean executed = statement.execute(CREATE_USERS_QUERY);
            System.out.printf("Таблица users создана %s\n", executed);//todo: все логи - на слое service
        } catch (SQLException e) {
            //todo:
            throw new RuntimeException("...роняем приложение, если дальнейшая работа не целесообразна: в ином случае - stackTrace " + e.getMessage());
        }
    }

    public void dropUsersTable() {//todo: codeStyle
        try (var connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS users";
            var executed = statement.execute(sql);
            System.out.printf("Таблица users удалена %s\n", executed);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (var connection = Util.getConnection();
             var preparedStatement = connection.prepareStatement(sql)

        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.printf("%s добавлен в базу данных.\n", name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?;";
        try (var connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setLong(1, id);
            var executeResult = preparedStatement.executeUpdate();
            System.out.printf("%d Строк(а) удалено(а).\n", executeResult);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userFromDb = new ArrayList<>();
        try (var connection = Util.getConnection();
             Statement statement = connection.createStatement()
        ) {
            String sql = "SELECT * FROM users;";
            var executeResult = statement.executeQuery(sql);
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

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (var connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
