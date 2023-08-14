package de.marshal.hw20230813.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface Hasher {
    String hash(String password) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
