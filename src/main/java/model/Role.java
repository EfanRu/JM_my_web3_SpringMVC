package model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public Role() {}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
