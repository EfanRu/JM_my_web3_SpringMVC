package service;

import com.sun.istack.Nullable;
import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {}

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

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
        return userDao.updateUser(id, firstName, lastName, phoneNumber, role, login, password);
    }

    public boolean checkAuth(String login, String password) {
        return userDao.checkAuth(login, password);
    }

    @Override
    @Nullable
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public User getUserById(String id) {
        User user = userDao.getUserById(id);
        return user == null ? new User() : user;
    }
}
