package de.marshal.hw20230813.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Pbkdf2HasherTest {
    @Test
    void testConstructorThrowsExceptionOnNullSalt() {
        assertThrows(IllegalArgumentException.class, () -> new Pbkdf2Hasher(null));
    }

    @ParameterizedTest
    @CsvSource({
            "password,salt,Zvs+rerJoCcUJkK1kzWWE9WHyhvjYLi1PYTtpceEV04=",
            "p,s,pvdo2H02318+QnBw5pYBJ2IUs+xSeEY9PV2ZaLl/V8I=",
    })
    void testHash(String password, String salt, String result) {
        Pbkdf2Hasher hasher = new Pbkdf2Hasher(salt.getBytes());

        assertDoesNotThrow(() -> {
            assertEquals(result, hasher.hash(password));
        });
    }

    @Test
    void testHashThrowsExceptionWhenArgIsNull() {
        Pbkdf2Hasher hasher = new Pbkdf2Hasher(new byte[0]);

        assertThrows(IllegalArgumentException.class, () -> hasher.hash(null));
    }
}