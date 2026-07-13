package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;
// ◀ Slides: Deck 02 Fundamentals — "Coupling in OOP: Subtyping & Message"

/**
 * FIXED — message coupling, the loosest bond.
 *
 * <p>{@code Orders} talks to a {@link Mailer} only through messages; it shares no
 * internal state and does not know which implementation it holds. The mailer is
 * injected, so any mailer — SMTP, a queue, a test double — drops in with no change
 * here. Depend on abstractions, not on concretions (DIP).
 */
public final class Orders {

    private final Mailer mailer;

    public Orders(Mailer mailer) {
        this.mailer = mailer;
    }

    public void place(Order order) {
        // Only a message crosses the boundary — nothing else is shared.
        mailer.send(order.receipt());
    }
}
