package dao;

import com.sun.istack.Nullable;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private EntityManagerFactory emf;
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createNamedQuery("getAllUsers");
        return query.getResultList();
    }

    @Transactional
    @Override
    public boolean addUser(User u) {
        try {
            entityManager.persist(u);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteUser(String id) {
        try {
            Query query = entityManager.createNamedQuery("deleteUser");
            query.setParameter("id", Long.parseLong(id));
            query.executeUpdate();
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password) {
        try {
            entityManager.merge(new User(Long.parseLong(id), firstName, lastName, login, password, Long.parseLong(phoneNumber), Role.parseRole(role)));
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login) {
        try {
            entityManager.merge(new User(Long.parseLong(id), firstName, lastName, login, getUserById(id).getPassword(), Long.parseLong(phoneNumber), Role.parseRole(role)));
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkAuth(String login, String password) {
        try {
            Query query = entityManager.createNamedQuery("checkAuth");
            query.setParameter("login", login);
            query.setParameter("password", password);
            return query.getSingleResult() != null;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Nullable
    @Override
    public User getUserByLogin(String login) {
        try {
            Query query = entityManager.createNamedQuery("getUserByLogin");
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    @Override
    public User getUserById(String id) {
        try {
            Query query = entityManager.createNamedQuery("getUserById");
            query.setParameter("id", Long.parseLong(id));
            return (User) query.getSingleResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
