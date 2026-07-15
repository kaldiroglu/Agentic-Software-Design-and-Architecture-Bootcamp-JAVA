package dev.kaldiroglu.bootcamp.fundamentals.cohesion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
