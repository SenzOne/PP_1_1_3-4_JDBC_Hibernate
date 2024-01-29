package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

            String sql = """
                    INSERT INTO users (name) VALUES ('name');
                     """;

            var executeResult = statement.execute(sql);
            System.out.println(executeResult);
            System.out.println(connection.getSchema());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
