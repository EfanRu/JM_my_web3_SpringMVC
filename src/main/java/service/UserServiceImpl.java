package service;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService;
    @Autowired
    private UserDao userDao;

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
      }
        return userService;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public boolean addUser(User u) {
        return userDao.addUser(u);
    }

    @Override
    @Transactional
    public boolean delUser(String id) {
        return userDao.delUser(id);
    }

    @Override
    @Transactional
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password) {
        return userDao.updateUser(id, firstName, lastName, phoneNumber, role, login, password);
    }

    @Override
    @Transactional
    public User checkAuth(String login, String password) {
        return userDao.checkAuth(login, password);
    }
}
