package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;
// ◀ Slides: Deck 02 Fundamentals — "Coupling in OOP: Subtyping & Message"

/**
 * FIXED — favour composition over inheritance.
 *
 * <p>This audit ledger <em>holds</em> a {@link Ledger} and uses only its public
 * API, so the ledger's internals can change freely without touching this class.
 * Deliberate, low coupling through a message — instead of an inherited weld.
 */
public final class AuditLedger {

    private final Ledger ledger;

    public AuditLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public int auditTotal() {
        return ledger.total();   // depends only on the public surface
    }
}
