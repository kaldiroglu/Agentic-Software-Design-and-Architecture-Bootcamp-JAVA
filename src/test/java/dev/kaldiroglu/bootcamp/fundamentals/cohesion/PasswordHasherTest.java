package dev.kaldiroglu.bootcamp.fundamentals.cohesion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Hashing lives in the port, not on Password. The hasher turns a live password into
 * a durable HashedPassword and verifies a candidate against it — and, because each
 * hash is salted, the same password stores differently every time yet still verifies.
 */
class PasswordHasherTest {

    private final PasswordHasher hasher = new Pbkdf2PasswordHasher();

    @Test
    void hashesAndVerifiesWithoutStoringThePlaintext() {
        HashedPassword stored = hasher.hash(Password.of("Sup3r-Secret!!"));

        assertTrue(hasher.verify(Password.of("Sup3r-Secret!!"), stored));
        assertFalse(hasher.verify(Password.of("Wr0ng-Secret!!"), stored));
    }

    @Test
    void saltMakesEachHashUniqueYetVerifiable() {
        var password = Password.of("Sup3r-Secret!!");
        HashedPassword first = hasher.hash(password);
        HashedPassword second = hasher.hash(password);

        assertNotEquals(first.encoded(), second.encoded());   // different salt each time
        assertTrue(hasher.verify(password, first));
        assertTrue(hasher.verify(password, second));
    }
}
