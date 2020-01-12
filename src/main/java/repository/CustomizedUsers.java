package repository;

public interface CustomizedUsers<T> {
        boolean checkAuth(String login, String password);
        T getUserByLogin(String login);
}
