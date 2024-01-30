package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User u1 =  new User("alice", "Smith", (byte) 25);
        List<User> users = new ArrayList<>(List.of(
                new User("alice", "Smith", (byte) 25),
                new User("john", "Doe", (byte) 30),
                new User("emma", "Johnson", (byte) 22),
                new User("michael", "Williams", (byte) 35),
                new User("sophia", "Brown", (byte) 28)
        ));


        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        // userService.saveUser(u1.getName(), u1.getLastName(), u1.getAge());
        userService.getAllUsers().forEach(System.out::println);
        //userService.dropUsersTable();

    }
}
