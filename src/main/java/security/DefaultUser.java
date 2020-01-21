package security;

import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import service.UserService;

public class DefaultUser {
    @Autowired
    private UserService userService;

    @Bean
    public void addInDbDefaultUser() {
        userService.addUser(new User("default user", "default user", "user", "user", 99L, new Role("user")));
    }
}
