package dev.kaldiroglu.bootcamp.cleancode;

import dev.kaldiroglu.bootcamp.cleancode.guardclauses.Eligibility;
import dev.kaldiroglu.bootcamp.cleancode.guardclauses.EligibilitySmell;
import dev.kaldiroglu.bootcamp.cleancode.honesterrors.UserFinder;
import dev.kaldiroglu.bootcamp.cleancode.honesterrors.UserFinderSmell;
import dev.kaldiroglu.bootcamp.cleancode.honesterrors.UserNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CleanCodeTest {

    private static final Map<String, String> USERS = Map.of("u1", "Ada");

    @Test
    void honestErrorsThrowInsteadOfReturningNull() {
        assertThrows(UserNotFoundException.class, () -> new UserFinder(USERS).find("missing"));
        assertEquals("Ada", new UserFinder(USERS).find("u1"));
    }

    @Test
    void theSmellHidesTheFailureAsNull() {
        assertNull(new UserFinderSmell(USERS).find("missing"));
    }

    @Test
    void guardClausesPreserveBehaviour() {
        var smell = new EligibilitySmell();
        var fixed = new Eligibility();
        int[][] cases = {{25, 1, 0}, {16, 1, 0}, {30, 0, 0}, {40, 1, 1}};
        for (int[] c : cases) {
            String expected = smell.describe(c[0], c[1] == 1, c[2] == 1);
            String actual = fixed.describe(c[0], c[1] == 1, c[2] == 1);
            assertEquals(expected, actual);
        }
        assertEquals("eligible", fixed.describe(25, true, false));
        assertEquals("banned", fixed.describe(25, true, true));
    }
}
