package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>(List.of(
                new User("alice", "Smith", (byte) 25),
                new User("john", "Doe", (byte) 30),
                new User("emma", "Johnson", (byte) 22),
                new User("michael", "Williams", (byte) 35),
                new User("sophia", "Brown", (byte) 28)
        ));


        UserService userService = new UserServiceImpl();
        // userService.createUsersTable();
        userService.saveUser("u1", "ln1", (byte) 2);
         //userService.dropUsersTable();

    }
}
