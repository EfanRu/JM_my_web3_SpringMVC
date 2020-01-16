package dao;

import com.sun.istack.Nullable;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        Query query = em.createNamedQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public boolean addUser(User u) {
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                try {
                    em.getTransaction().rollback();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("DELETE u FROM User WHERE u.id = :id");
            query.setParameter("id", Long.parseLong(id));
            query.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                try {
                    em.getTransaction().rollback();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password) {
        try {
            em.getTransaction().begin();
            em.merge(new User(Long.parseLong(id), firstName, lastName, login, password, Long.parseLong(phoneNumber), Role.parseRole(role)));
            em.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                try {
                    em.getTransaction().rollback();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkAuth(String login, String password) {
        try {
            Query query = em.createNamedQuery("SELECT u FROM User WHERE u.login = :login AND u.password = :password");
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
            Query query = em.createNamedQuery("SELECT u FROM User WHERE u.login = :login");
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
