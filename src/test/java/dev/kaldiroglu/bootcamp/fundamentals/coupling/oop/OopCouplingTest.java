package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Message coupling gives us a seam: inject a fake mailer and assert exactly what
 * crossed the boundary. Programming to an implementation gives us none. And
 * subtyping welds a subclass to its parent's data, while composition leans only
 * on the public API.
 */
class OopCouplingTest {

    @Test
    void messageCouplingLetsUsInjectAndVerifyAnyMailer() {
        var mailer = new RecordingMailer();
        new Orders(mailer).place(new Order("o1"));
        assertEquals(1, mailer.sent().size());
        assertEquals("Receipt for order o1", mailer.sent().get(0));
    }

    @Test
    void programmingToAnImplementationLeavesNoSeam() {
        // The smell runs, but there is no way to intercept the mail it sends.
        assertDoesNotThrow(() -> new OrdersSmell().place(new Order("o2")));
    }

    @Test
    void subtypingSmellAndComposedFixAgree() {
        var smell = new AuditLedgerSmell();
        smell.add(10);
        smell.add(32);
        assertEquals(42, smell.auditTotal());

        var base = new Ledger();
        base.add(10);
        base.add(32);
        assertEquals(42, new AuditLedger(base).auditTotal());
    }
}
