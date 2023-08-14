package de.marshal.hw20230813.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

// https://www.baeldung.com/java-password-hashing
// https://github.com/spring-projects/spring-security/blob/main/crypto/src/main/java/org/springframework/security/crypto/password/Pbkdf2PasswordEncoder.java#L243
public class Pbkdf2Hasher implements Hasher {
    private byte[] salt;

    public Pbkdf2Hasher(byte[] salt) {
        if (salt == null) {
            throw new IllegalArgumentException("null salt given");
        }

        this.salt = salt;
    }

    @Override
    public String hash(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (password == null) {
            throw new IllegalArgumentException("null password given");
        }

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 310000, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }
}
