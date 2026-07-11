package dev.kaldiroglu.bootcamp.fundamentals.coupling;
// ◀ Slides: Deck 02 Fundamentals — "Tight vs Loose Coupling"

import java.util.List;

/** FIXED — the abstraction the report depends on, instead of a concrete engine. */
public interface Database {

    List<String> query(String sql);
}
