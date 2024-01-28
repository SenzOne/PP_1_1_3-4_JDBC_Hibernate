package jm.task.core.jdbc;


import jm.task.core.jdbc.util.Util;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        try (var connection = Util.open()) {
            System.out.println(connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
