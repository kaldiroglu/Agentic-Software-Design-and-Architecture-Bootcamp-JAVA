package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;
// ◀ Slides: Deck 02 Fundamentals — "Coupling in OOP: Subtyping & Message"

/**
 * SMELL — "program to an implementation".
 *
 * <p>{@code OrdersSmell} creates its own concrete mailer with {@code new}, welding
 * itself to that one technology. You cannot swap the mailer, and you cannot test
 * the order flow without really sending mail — there is no seam to inject a double.
 */
public final class OrdersSmell {

    // Hard-wired dependency on a concretion — the root of the coupling.
    private final SmtpMailer mailer = new SmtpMailer();

    public void place(Order order) {
        mailer.send(order.receipt());
    }

    /** Stands in for a heavyweight SMTP client you can't use in a unit test. */
    private static final class SmtpMailer {
        void send(String receipt) {
            // Pretend this opens a real socket and talks to a mail server.
        }
    }
}
