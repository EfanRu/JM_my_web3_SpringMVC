package security;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import service.UserService;

@Component
@PropertySource("classpath:DB.properties")
public class DefaultUserAndAdmin {
    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

    public DefaultUserAndAdmin() {
        User user = new User();
        user.setLogin(env.getProperty("db.default.user.login"));
        user.setPassword(env.getProperty("db.default.user.password"));
        userService.addUser(user);

        User admin = new User();
        user.setLogin(env.getProperty("db.default.admin.login"));
        user.setPassword(env.getProperty("db.default.admin.password"));
        userService.addUser(admin);
    }
}
