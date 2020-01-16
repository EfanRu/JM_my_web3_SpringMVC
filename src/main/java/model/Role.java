package model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public Role() {}

    public Role(Integer id) {
        this.id = id;
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Role parseRole(String role) {
        Role result = new Role();
        if (role.toLowerCase().equals("admin")) {
            result.name = "admin";
        } else if (role.toLowerCase().equals("user")) {
            result.name = "user";
        } else {
            result.name = "anonymous";
        }
        return result;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
