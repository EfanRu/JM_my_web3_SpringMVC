package dao;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    boolean addUser(User u);
    boolean delUser(String id);
    boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password);
    User checkAuth(String login, String password);
    void dropTable();
}
