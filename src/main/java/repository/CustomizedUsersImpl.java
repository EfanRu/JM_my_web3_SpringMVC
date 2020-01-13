package repository;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomizedUsersImpl implements CustomizedUsers {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean checkAuth(String login, String password) {
        String hql = "SELECT u FROM User u WHERE u.login = ?1 and u.password = ?2";
        Query query = entityManager.createQuery(hql, User.class);
        query.setParameter(1, login);
        query.setParameter(2, password);
        return query.getSingleResult() != null;
    }

    @Override
    public User getUserByLogin(String login) {
        String hql = "SELECT u FROM User u WHERE u.login = ?1";
        Query query = entityManager.createQuery(hql, User.class);
        query.setParameter(1, login);
        return (User) query.getSingleResult();
    }
}
