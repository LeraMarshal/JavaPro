package de.marshal.hw20230813.service;

import de.marshal.hw20230813.db.UserDatabase;
import de.marshal.hw20230813.entity.User;
import de.marshal.hw20230813.exception.NotAuthenticatedException;
import de.marshal.hw20230813.util.Hasher;

import java.util.Objects;

public class AuthenticationService {
    private UserDatabase userDatabase;
    private Hasher hasher;

    public AuthenticationService(UserDatabase userDatabase, Hasher hasher) {
        this.userDatabase = userDatabase;
        this.hasher = hasher;
    }

    public void authenticate(String username, String password) throws NotAuthenticatedException {
        if (username == null || username.isBlank()) {
            throw new NotAuthenticatedException("null or blank username given");
        }

        if (password == null || password.isBlank()) {
            throw new NotAuthenticatedException("null or blank password given");
        }

        try {
            String hashedPassword = hasher.hash(password);
            User user = userDatabase.getUserByUsername(username);

            if (Objects.equals(hashedPassword, user.getPassword())) {
                return;
            }
        } catch (Exception ignored) {
        }

        throw new NotAuthenticatedException("invalid username or password");
    }

    public void grantAccess(User user) {
        if (user == null) {
            throw new IllegalArgumentException("null user given");
        }

        user.setAdmin(true);
    }

    public void revokeAccess(User user) {
        if (user == null) {
            throw new IllegalArgumentException("null user given");
        }

        user.setAdmin(false);
    }
}
