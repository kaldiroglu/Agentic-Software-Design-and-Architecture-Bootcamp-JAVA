package dev.kaldiroglu.bootcamp.fundamentals.coupling;
// ◀ Slides: Deck 02 Fundamentals — "Tight vs Loose Coupling"

/**
 * FIXED — the report depends on the {@link Database} abstraction, injected from
 * outside. Swap MySQL for Postgres or an in-memory fake with no change here.
 */
public final class Report {

    private final Database db;

    public Report(Database db) {
        this.db = db;
    }

    public int rowCount() {
        return db.query("SELECT * FROM sales").size();
    }
}
