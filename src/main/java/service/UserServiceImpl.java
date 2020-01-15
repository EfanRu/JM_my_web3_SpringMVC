package service;

import com.sun.istack.Nullable;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    public UserServiceImpl() {}

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean addUser(User u) {
        return u.equals(userRepository.save(u));
    }

    @Override
    public boolean deleteUser(String id) {
        try {
            userRepository.deleteById(Long.parseLong(id));
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password) {
        User user = new User(Long.parseLong(id), firstName, lastName, login, password, Long.parseLong(phoneNumber), role);
        return user.equals(userRepository.save(user));
    }

    public boolean checkAuth(String login, String password) {
//        return userRepository.checkAuth(login, password).anyMatch(u -> u.getLogin().equals(login) && u.getPassword().equals(password));
        return userRepository.checkAuth(login, password);
    }

    @Override
    @Nullable
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
