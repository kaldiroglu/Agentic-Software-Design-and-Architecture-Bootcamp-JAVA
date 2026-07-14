package dev.kaldiroglu.bootcamp.fundamentals.cohesion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The behaviour is identical either way; cohesion is about STRUCTURE. Each
 * focused class can be understood and tested on its own — unlike the God utility.
 */
class CohesionTest {

    @Test
    void focusedClassesEachDoOneJob() {
        var repo = new UserRepository();
        repo.save("u1", "Ada");
        assertEquals("Ada", repo.findName("u1").orElseThrow());

        assertEquals(20.0, new TaxCalculator(0.20).taxOn(100.0), 0.001);

        String mail = new EmailComposer().compose("a@b.com", "Hi", "Body");
        assertTrue(mail.contains("a@b.com"));
    }

    @Test
    void theSmellStillWorks_butMixesEverything() {
        var utils = new UtilsSmell();
        utils.saveUser("u1", "Ada");
        assertEquals("Ada", utils.loadUser("u1"));
        assertEquals(20.0, utils.calcTax(100.0), 0.001);
        assertEquals("2026-07-11", utils.formatIsoDate(2026, 7, 11));
    }

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
