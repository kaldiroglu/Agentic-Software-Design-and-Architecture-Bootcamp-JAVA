package dev.kaldiroglu.bootcamp.secure;

import dev.kaldiroglu.bootcamp.secure.errorleak.ErrorResponder;
import dev.kaldiroglu.bootcamp.secure.secrets.Secrets;
import dev.kaldiroglu.bootcamp.secure.sqlinjection.ParameterizedQuery;
import dev.kaldiroglu.bootcamp.secure.sqlinjection.SafeUserDao;
import dev.kaldiroglu.bootcamp.secure.sqlinjection.VulnerableUserDao;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecureCodingTest {

    @Nested
    class SqlInjection {
        private static final String ATTACK = "' OR '1'='1";

        @Test
        void concatenationLetsTheAttackerRewriteTheQuery() {
            String sql = new VulnerableUserDao().buildQuery(ATTACK);
            // The malicious text became part of the SQL: an always-true WHERE clause.
            assertTrue(sql.contains("OR '1'='1"));
        }

        @Test
        void parameterizationKeepsInputAsData() {
            ParameterizedQuery q = new SafeUserDao().buildQuery(ATTACK);
            // The SQL is a fixed template with no user text; the attack is just a value.
            assertEquals("SELECT * FROM users WHERE name = ?", q.sql());
            assertFalse(q.sql().contains("OR"));
            assertEquals(ATTACK, q.parameters().getFirst());
        }
    }

    @Nested
    class ErrorLeak {
        @Test
        void safeMessageHidesInternalsThatTheSmellExposes() {
            var responder = new ErrorResponder();
            var boom = new IllegalStateException("column 'ssn' at db-prod-7:5432");

            assertTrue(responder.smellUserMessage(boom).contains("ssn"));
            assertFalse(responder.safeUserMessage(boom).contains("ssn"));
            assertTrue(responder.internalLog(boom).contains("ssn"));
        }
    }

    @Nested
    class SecretsHandling {
        @Test
        void secretComesFromConfigNotFromSource() {
            var secrets = new Secrets(Map.of("API_KEY", "sk_test_from_env"));
            assertEquals("sk_test_from_env", secrets.apiKey());
        }

        @Test
        void missingSecretFailsLoudly() {
            var secrets = new Secrets(Map.of());
            assertThrows(IllegalStateException.class, secrets::apiKey);
        }
    }
}
