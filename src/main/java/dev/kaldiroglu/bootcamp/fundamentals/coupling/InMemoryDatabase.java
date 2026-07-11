package dev.kaldiroglu.bootcamp.fundamentals.coupling;
// ◀ Slides: Deck 02 Fundamentals — "Tight vs Loose Coupling"

import java.util.List;

/**
 * FIXED — a lightweight {@link Database} for tests and demos. Because {@code Report}
 * depends on the interface, we can swap this in with no change to the report.
 */
public final class InMemoryDatabase implements Database {

    private final List<String> rows;

    public InMemoryDatabase(List<String> rows) {
        this.rows = List.copyOf(rows);
    }

    @Override
    public List<String> query(String sql) {
        return rows;
    }
}
