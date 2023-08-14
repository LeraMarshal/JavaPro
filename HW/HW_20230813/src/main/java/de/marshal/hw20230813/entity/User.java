package de.marshal.hw20230813.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;

    private boolean admin = false;

    public User(int id, String username, String password, String email, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getUserInfo() {
        return toString();
    }

    public void addRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("null role given");
        }

        if (roles.contains(role)) {
            return;
        }

        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", admin=" + admin +
                '}';
    }
}
