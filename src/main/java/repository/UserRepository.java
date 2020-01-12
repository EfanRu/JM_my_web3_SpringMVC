package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>, CustomizedUsers<User> {
/*
    @Query(value = "SELECT u FROM User u WHERE u.login = :login and u.password = :password")
    Stream<User> checkAuth(@Param("login")String login, @Param("password")String password);

    @Query(value = "SELECT u FROM User u WHERE u.login = :login")
    User getUserByLogin(@Param("login") String login);*/
}