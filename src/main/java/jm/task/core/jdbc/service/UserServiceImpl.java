package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;


public class UserServiceImpl implements UserService {
    UserDao userDaoJDBC;

    public UserServiceImpl() {
        this.userDaoJDBC = new UserDaoJDBCImpl();
    }

    public void createUsersTable() {
        System.out.println("таблица создана\n");
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        System.out.println("Таблица удалена\n");
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.printf("%s добавлен в базу данных.\n", name);
    }

    public void removeUserById(long id) {
        System.out.println("Пользователи удалены: \n");
        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        System.out.println("Получены пользователи:\n");
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        System.out.println("Таблица очищена.\n");
        userDaoJDBC.cleanUsersTable();
    }
}
