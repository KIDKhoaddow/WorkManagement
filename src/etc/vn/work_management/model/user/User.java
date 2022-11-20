package etc.vn.work_management.model.user;

public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Role role;

    public User() {
    }

    public User(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = Role.EMPLOYEE;
    }

    public User(Long id, String email, String name, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
