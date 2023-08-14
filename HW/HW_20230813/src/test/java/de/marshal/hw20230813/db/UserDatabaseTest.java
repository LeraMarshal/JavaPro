package de.marshal.hw20230813.db;

import de.marshal.hw20230813.entity.User;
import de.marshal.hw20230813.exception.UserAlreadyExistsException;
import de.marshal.hw20230813.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDatabaseTest {
    private List<User> mockedUsers;

    private UserDatabase userDatabase;

    @BeforeEach
    void setup() throws Exception {
        mockedUsers = new ArrayList<>();
        userDatabase = new UserDatabase();

        for (int i = 0; i < 3; i++) {
            User mockedUser = Mockito.mock(User.class);

            Mockito.when(mockedUser.getId()).thenReturn(i);
            Mockito.when(mockedUser.getUsername()).thenReturn("user" + i);
            Mockito.when(mockedUser.getPassword()).thenReturn("password" + i);

            mockedUsers.add(mockedUser);
            userDatabase.addUser(mockedUser);
        }
    }

    @Test
    void testGetUserById() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < mockedUsers.size(); i++) {
                assertEquals(mockedUsers.get(i), userDatabase.getUserById(i));
            }
        });
    }

    @Test
    void testGetUserByIdThrowsExceptionWhenUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userDatabase.getUserById(10));
    }

    @Test
    void testGetUserByUsername() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < mockedUsers.size(); i++) {
                assertEquals(mockedUsers.get(i), userDatabase.getUserByUsername("user" + i));
            }
        });
    }

    @Test
    void testGetUserByUsernameThrowsExceptionWhenUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userDatabase.getUserByUsername("bad"));
    }

    @Test
    void testAddUser() {
        int mockedUserId = getNextUserId();

        User mockedUser = Mockito.mock(User.class);
        Mockito.when(mockedUser.getId()).thenReturn(mockedUserId);
        Mockito.when(mockedUser.getUsername()).thenReturn("user" + mockedUserId);
        Mockito.when(mockedUser.getPassword()).thenReturn("password" + mockedUserId);

        assertDoesNotThrow(() -> {
            userDatabase.addUser(mockedUser);

            assertEquals(mockedUser, userDatabase.getUserById(mockedUserId));
            assertEquals(mockedUser, userDatabase.getUserByUsername("user" + mockedUserId));
        });

        Mockito.verifyNoMoreInteractions(mockedUser);
    }

    @Test
    void testAddUserThrowsExceptionWhenUserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> userDatabase.addUser(null));
    }

    @Test
    void testAddUserThrowsExceptionWhenUserUsernameIsNull() {
        User mockedUser = Mockito.mock(User.class);

        Mockito.when(mockedUser.getUsername()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> userDatabase.addUser(mockedUser));

        Mockito.verifyNoMoreInteractions(mockedUser);
    }

    @Test
    void testAddUserThrowsExceptionWhenUserPasswordIsNull() {
        User mockedUser = Mockito.mock(User.class);
        Mockito.when(mockedUser.getUsername()).thenReturn("user");
        Mockito.when(mockedUser.getPassword()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> userDatabase.addUser(mockedUser));
        Mockito.verifyNoMoreInteractions(mockedUser);
    }

    @Test
    void testAddUserThrowsExceptionWhenUserIdAlreadyExists() {
        User mockedUser = Mockito.mock(User.class);

        Mockito.when(mockedUser.getPassword()).thenReturn("password");
        Mockito.when(mockedUser.getUsername()).thenReturn("user");
        Mockito.when(mockedUser.getId()).thenReturn(2);

        assertThrows(UserAlreadyExistsException.class, () -> userDatabase.addUser(mockedUser));

        Mockito.verifyNoMoreInteractions(mockedUser);
    }

    @Test
    void testAddUserThrowsExceptionWhenUserUsernameAlreadyExists() {
        int nextUserId = getNextUserId();

        User mockedUser = Mockito.mock(User.class);

        Mockito.when(mockedUser.getPassword()).thenReturn("password");
        Mockito.when(mockedUser.getUsername()).thenReturn("user1");
        Mockito.when(mockedUser.getId()).thenReturn(nextUserId);

        assertThrows(UserAlreadyExistsException.class, () -> userDatabase.addUser(mockedUser));

        Mockito.verifyNoMoreInteractions(mockedUser);
    }

    int getNextUserId() {
        return 1 + mockedUsers.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0);
    }
}