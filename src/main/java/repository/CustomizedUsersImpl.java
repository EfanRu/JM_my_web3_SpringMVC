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
//        String hql = "SELECT u FROM User u WHERE u.login = :login and u.password = :password";
//        Query query = entityManager.createQuery(hql);
//        query.setParameter("login", login);
//        query.setParameter("password", password);
//        return entityManager.createQuery(hql, User.class).getSingleResult() != null;
//

        String hql = "SELECT * FROM user WHERE login = " + login + " and password = " + password;
        Query query = entityManager.createQuery(hql);
//        query.setParameter("login", login);
//        query.setParameter("password", password);
        return entityManager.createQuery(hql, User.class).getSingleResult() != null;
    }

    @Override
    public User getUserByLogin(String login) {
//        String hql = "SELECT u FROM User u WHERE u.login = :login";
        String hql = "SELECT * FROM user WHERE login = " + login;
        Query query = entityManager.createQuery(hql);
//        query.setParameter("login", login);
        return entityManager.createQuery(hql, User.class).getSingleResult();
    }
}
