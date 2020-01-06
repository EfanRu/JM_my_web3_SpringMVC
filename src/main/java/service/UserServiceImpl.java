package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {}

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean addUser(User u) {
        return u.equals(userRepository.save(u));
    }

    @Override
    @Transactional
    public boolean delUser(String id) {
        try {
            userRepository.deleteById(Integer.parseInt(id));
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password) {
        User u = new User(Long.parseLong(id), firstName, lastName, login, password, Long.parseLong(phoneNumber), role);
        return u.equals(userRepository.save(u));
    }

    @Override
    public boolean checkAuth(String login, String password) {
        return userRepository.checkAuth(login, password).anyMatch(u -> u.getLogin().equals(login) && u.getPassword().equals(password));
    }
}
