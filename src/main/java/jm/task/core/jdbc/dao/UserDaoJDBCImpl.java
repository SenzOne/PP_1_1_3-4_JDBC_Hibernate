package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (var connection = Util.open()) {
            Statement statement = connection.createStatement();
            String sql = """
                    CREATE TABLE IF NOT EXISTS users (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(50),
                    lastName VARCHAR(25),
                    age INT
                    );
                    """;
            var executed = statement.executeUpdate(sql);
            System.out.println(executed);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (var connection = Util.open()) {
            Statement statement = connection.createStatement();

            String SQL = "DROP TABLE IF EXISTS users";

            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (var connection = Util.open();
             Statement statement = connection.createStatement();
        ) {

            String sql =
                    String.format("INSERT INTO users (name, lastName, age) " +
                                  "VALUES ('%s', '%s', %d)", name, lastName, age);

            var executeResult = statement.executeUpdate(sql);
            System.out.println(executeResult);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> userFromDb = new ArrayList<>();
        try (var connection = Util.open();
             Statement statement = connection.createStatement();
        ) {

            String sql ="SELECT * FROM users;";

            var executeResult = statement.executeQuery(sql);
            while (executeResult.next()) {
                User user = new User();
                        user.setId((long) executeResult.getInt("id"));
                        user.setName(executeResult.getString("name"));
                        user.setLastName(executeResult.getString("lastname"));
                        user.setAge ((byte) executeResult.getInt("age"));

                userFromDb.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return userFromDb;
    }

    public void cleanUsersTable() {

    }
}
