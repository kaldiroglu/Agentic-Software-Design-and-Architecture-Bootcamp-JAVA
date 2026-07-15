package dev.kaldiroglu.bootcamp.fundamentals.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Functional cohesion in the small: presentation lives in PasswordFormatter and
 * construction in PasswordFactory, so each grows on its own without bloating the
 * Password value object.
 */
class PasswordSplitTest {

    private final PasswordFormatter formatter = new PasswordFormatter();
    private final PasswordFactory factory = new PasswordFactory();

    @Test
    void formatterPresentsAPasswordSafely() {
        var password = Password.of("Sup3r-Secret!!");   // 14 chars
        assertEquals("[redacted: 14 chars]", formatter.redactedForLog(password));
        assertEquals("Su••••••••••••", formatter.showingFirst(password, 2));
    }

    @Test
    void factoryBuildsValidPasswordsSeveralWays() {
        assertEquals(16, factory.temporary().masked().length());
        assertEquals(24, factory.resetToken().masked().length());
        assertNotEquals(factory.temporary(), factory.temporary());   // each is unique

        assertTrue(factory.fromRaw("Sup3r-Secret!!").matches("Sup3r-Secret!!"));
        assertThrows(IllegalArgumentException.class, () -> factory.fromRaw("weak"));
    }
}
