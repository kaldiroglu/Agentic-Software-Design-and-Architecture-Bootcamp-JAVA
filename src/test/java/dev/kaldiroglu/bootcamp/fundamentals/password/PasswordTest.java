package dev.kaldiroglu.bootcamp.fundamentals.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Password value object keeps everything about a password together —
 * validation, random creation, comparison, and masking — in one cohesive class.
 */
class PasswordTest {

    @Test
    void passwordValueObjectKeepsAllItsBehaviourTogether() {
        var password = Password.of("Sup3r-Secret!!");   // 14 chars: upper, lower, digit, symbol

        // validation
        assertFalse(Password.isValid("password"));       // too weak
        assertThrows(IllegalArgumentException.class, () -> Password.of("short"));

        // comparison
        assertTrue(password.matches("Sup3r-Secret!!"));
        assertFalse(password.matches("Sup3r-Secret!?"));

        // formatting & masking — the raw value never leaks
        assertEquals("••••••••••••••", password.masked());
        assertEquals("••••••••••••!!", password.maskedShowingLast(2));
        assertEquals(password.masked(), password.toString());

        // random creation: valid by construction, and each one differs
        var random = Password.random(16);
        assertEquals(16, random.masked().length());
        assertNotEquals(random, Password.random(16));
    }
}
