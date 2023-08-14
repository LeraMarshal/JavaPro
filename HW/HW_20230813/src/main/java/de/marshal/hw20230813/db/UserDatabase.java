package de.marshal.hw20230813.db;

import de.marshal.hw20230813.entity.User;
import de.marshal.hw20230813.exception.UserAlreadyExistsException;
import de.marshal.hw20230813.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDatabase {
    private List<User> users = new ArrayList<>();

    public User getUserById(int id) throws UserNotFoundException {
        return users.stream()
                .filter(user -> id == user.getId())
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        return users.stream()
                .filter(user -> Objects.equals(username, user.getUsername()))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
    }

    public void addUser(User user) throws UserAlreadyExistsException {
        if (user == null) {
            throw new IllegalArgumentException("null user given");
        }

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("null or blank username given");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("null or blank password given");
        }

        if (users.stream().anyMatch(u -> user.getId() == u.getId() || Objects.equals(user.getUsername(), u.getUsername()))) {
            throw new UserAlreadyExistsException();
        }

        users.add(user);
    }
}
