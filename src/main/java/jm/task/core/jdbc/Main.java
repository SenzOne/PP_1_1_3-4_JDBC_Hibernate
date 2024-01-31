package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            runApp();
        } finally {
            Util.closePool();
        }
    }

    private static void runApp() {
        List<User> users = new ArrayList<>(List.of(
                new User("Alice", "Smith", (byte) 25),
                new User("John", "Doe", (byte) 30),
                new User("Emma", "Johnson", (byte) 22),
                new User("Michael", "Williams", (byte) 35),
                new User("Sophia", "Brown", (byte) 28)
        ));

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        users.forEach(x -> userService.saveUser(x.getName(), x.getLastName(), x.getAge()));
        userService.getAllUsers().forEach(System.out::println);
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
