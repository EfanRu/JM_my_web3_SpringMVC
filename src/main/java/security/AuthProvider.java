package security;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        User user = userService.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Login not found");
        }
        String password = authentication.getCredentials().toString();
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password for login: " + login);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole().getName().equals("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (user.getRole().getName().equals("user")) {
            authorities.add(new SimpleGrantedAuthority("USER"));
        } else {
            throw new BadCredentialsException("No such user role");
        }
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
