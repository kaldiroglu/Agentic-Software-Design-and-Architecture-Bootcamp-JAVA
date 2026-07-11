package dev.kaldiroglu.bootcamp.testing.doubles;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A hand-written test double (a spy/fake mailer) isolates the unit: no real e-mail
 * is sent, and we can verify exactly how the collaborator was used.
 */
class WelcomeServiceTest {

    /** Records every send instead of contacting a real mail server. */
    private static final class FakeMailer implements Mailer {
        record Sent(String to, String body) {
        }

        final List<Sent> sent = new ArrayList<>();

        @Override
        public void send(String to, String body) {
            sent.add(new Sent(to, body));
        }
    }

    @Test
    void sendsExactlyOneWelcomeMail() {
        var mailer = new FakeMailer();
        new WelcomeService(mailer).welcome("ada@x.com", "Ada");

        assertEquals(1, mailer.sent.size());
        assertEquals("ada@x.com", mailer.sent.getFirst().to());
        assertEquals("Welcome, Ada!", mailer.sent.getFirst().body());
    }
}
