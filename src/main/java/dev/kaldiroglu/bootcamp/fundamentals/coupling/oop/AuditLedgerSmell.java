package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;
// ◀ Slides: Deck 02 Fundamentals — "Coupling in OOP: Subtyping & Message"

/**
 * SMELL — subtyping coupling.
 *
 * <p>This subclass reaches into the parent's protected {@code entries} field,
 * binding itself to {@link Ledger}'s internal representation. Change how Ledger
 * stores entries — a different collection, a running total, a database — and this
 * class breaks, even though its public behaviour never changed. Inheritance made
 * the bond tight and invisible.
 */
public final class AuditLedgerSmell extends Ledger {

    public int auditTotal() {
        int sum = 0;
        for (int amount : entries) {   // welded to the parent's data shape
            sum += amount;
        }
        return sum;
    }
}
