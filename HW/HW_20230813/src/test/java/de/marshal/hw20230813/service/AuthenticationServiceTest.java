package de.marshal.hw20230813.service;

import de.marshal.hw20230813.db.UserDatabase;
import de.marshal.hw20230813.entity.User;
import de.marshal.hw20230813.exception.NotAuthenticatedException;
import de.marshal.hw20230813.exception.UserNotFoundException;
import de.marshal.hw20230813.util.Hasher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    private static Hasher mockedHasher;
    private static UserDatabase mockedUserDatabase;
    private static User mockedUser;

    private static AuthenticationService authenticationService;

    @BeforeAll
    static void setup() throws Exception {
        mockedUser = Mockito.mock(User.class);
        Mockito.when(mockedUser.getPassword()).thenReturn("hash1");

        mockedUserDatabase = Mockito.mock(UserDatabase.class);
        Mockito.when(mockedUserDatabase.getUserByUsername("user1"))
                .thenReturn(mockedUser);
        Mockito.when(mockedUserDatabase.getUserByUsername("user2"))
                .thenThrow(UserNotFoundException.class);

        mockedHasher = Mockito.mock(Hasher.class);
        Mockito.when(mockedHasher.hash("password1")).thenReturn("hash1");
        Mockito.when(mockedHasher.hash("password2")).thenReturn("hash2");
        Mockito.when(mockedHasher.hash("password3")).thenReturn("hash1");

        authenticationService = new AuthenticationService(mockedUserDatabase, mockedHasher);
    }

    @ParameterizedTest
    @CsvSource({
            "user1,password1,false",
            "user1,password3,false",
            "user1,password2,true",
            "user2,password1,true",
            "user1,,true",
            "user1,'',true",
            ",password,true",
            "'',password,true",
    })
    void testAuthenticate(String username, String password, boolean expectException) {
        if (expectException) {
            assertThrows(NotAuthenticatedException.class, () -> {
                authenticationService.authenticate(username, password);
            });
        } else {
            assertDoesNotThrow(() -> {
                authenticationService.authenticate(username, password);
            });
        }
    }

    @Test
    void testGrantAccess() {
        User user = Mockito.mock(User.class);

        assertDoesNotThrow(() -> {
            authenticationService.grantAccess(user);
        });

        Mockito.verify(user).setAdmin(true);
    }

    @Test
    void testGrantAccessThrowsExceptionOnNullUser() {
        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.grantAccess(null);
        });
    }

    @Test
    void testRevokeAccess() {
        User user = Mockito.mock(User.class);

        assertDoesNotThrow(() -> {
            authenticationService.revokeAccess(user);
        });

        Mockito.verify(user).setAdmin(false);
    }

    @Test
    void testRevokeAccessThrowsExceptionOnNullUser() {
        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.grantAccess(null);
        });
    }
}
