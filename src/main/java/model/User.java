package model;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "getUserByLogin", query = "FROM User u WHERE login = :login"),
        @NamedQuery(name = "getUserById", query = "FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "checkAuth", query = "FROM User u WHERE u.login = :login AND u.password = :password"),
        @NamedQuery(name = "deleteUser", query = "DELETE FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "getAllUsers", query = "FROM User u")
})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private Long phoneNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {}

    public User(Long id, String firstName, String lastName, String login, String password, Long phoneNumber, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public User(String firstName, String lastName, String login, String password, Long phoneNumber, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.id = null;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("User id=")
                .append(getId())
                .append(", first name=")
                .append(getFirstName())
                .append(", last name=")
                .append(getLastName())
                .append(", role=")
                .append(getRole())
                .append(", login=")
                .append(getLogin())
                .append(", password=")
                .append(getPassword())
                .toString();
    }
}
