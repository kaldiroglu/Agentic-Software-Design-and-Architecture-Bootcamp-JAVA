package dev.kaldiroglu.bootcamp.fundamentals.cohesion;
// ◀ Slides: Deck 02 Fundamentals — "A Password Value Object" (presenter note)

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * FIXED — the infrastructure adapter that implements {@link PasswordHasher}.
 *
 * <p>This is where all the volatile, dependency-heavy detail lives: the algorithm
 * (PBKDF2-HMAC-SHA256), a per-password random salt, and a work factor tuned over
 * time. Swap it for an Argon2 adapter and nothing about {@link Password} changes —
 * that is the whole point of keeping hashing out of the value object.
 */
public final class Pbkdf2PasswordHasher implements PasswordHasher {

    private static final int ITERATIONS = 210_000;   // tune upward as hardware improves
    private static final int KEY_BITS = 256;
    private static final int SALT_BYTES = 16;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public HashedPassword hash(Password password) {
        byte[] salt = new byte[SALT_BYTES];
        RANDOM.nextBytes(salt);
        byte[] key = derive(password.reveal(), salt, ITERATIONS);
        String encoded = ITERATIONS + ":" + encode(salt) + ":" + encode(key);
        return new HashedPassword(encoded);
    }

    @Override
    public boolean verify(Password candidate, HashedPassword stored) {
        String[] parts = stored.encoded().split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = decode(parts[1]);
        byte[] expected = decode(parts[2]);
        byte[] actual = derive(candidate.reveal(), salt, iterations);
        return MessageDigest.isEqual(expected, actual);   // constant-time
    }

    private static byte[] derive(String raw, byte[] salt, int iterations) {
        try {
            var spec = new PBEKeySpec(raw.toCharArray(), salt, iterations, KEY_BITS);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec)
                    .getEncoded();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("PBKDF2 is unavailable", e);
        }
    }

    private static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static byte[] decode(String text) {
        return Base64.getDecoder().decode(text);
    }
}
