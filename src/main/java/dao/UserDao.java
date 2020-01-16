package dao;

import model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    boolean addUser(User u);
    boolean deleteUser(String id);
    boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password);
    boolean checkAuth(String login, String password);
    User getUserByLogin(String login);
}
