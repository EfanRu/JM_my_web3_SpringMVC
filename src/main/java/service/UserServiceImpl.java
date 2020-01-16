package service;

import com.sun.istack.Nullable;
import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {}

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean addUser(User u) {
        return u.equals(userDao.addUser(u));
    }

    @Override
    public boolean deleteUser(String id) {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password) {
        return userDao.updateUser(id, firstName, lastName, phoneNumber, role, login, password));
    }

    public boolean checkAuth(String login, String password) {
        return userDao.checkAuth(login, password);
    }

    @Override
    @Nullable
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
