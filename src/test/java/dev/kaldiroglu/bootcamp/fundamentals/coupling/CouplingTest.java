package dev.kaldiroglu.bootcamp.fundamentals.coupling;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Loose coupling shows its value in the test itself: we inject a fake database
 * and drive the report to any result. The tightly-coupled version can only ever
 * return its hard-wired data.
 */
class CouplingTest {

    @Test
    void looselyCoupledReportAcceptsAnyDatabase() {
        var db = new InMemoryDatabase(List.of("a", "b", "c"));
        assertEquals(3, new Report(db).rowCount());
    }

    @Test
    void tightlyCoupledReportIsStuckWithItsWiring() {
        // No seam to inject test data — we are stuck with whatever it created.
        assertEquals(1, new ReportSmell().rowCount());
    }
}
