package dev.kaldiroglu.bootcamp.fundamentals.coupling;
// ◀ Slides: Deck 02 Fundamentals — "Tight vs Loose Coupling"

import java.util.List;

/**
 * SMELL — the report creates its own concrete data source with {@code new}.
 * It is welded to that one technology: you cannot swap the source, and you
 * cannot test the report without the real thing.
 */
public final class ReportSmell {

    // Hard-wired dependency — the root of the coupling.
    private final MySqlStub db = new MySqlStub();

    public int rowCount() {
        List<String> rows = db.query("SELECT * FROM sales");
        return rows.size();
    }

    /** Stands in for a real, heavyweight MySQL driver you can't use in a unit test. */
    private static final class MySqlStub {
        List<String> query(String sql) {
            return List.of("hard-wired-row");
        }
    }
}
