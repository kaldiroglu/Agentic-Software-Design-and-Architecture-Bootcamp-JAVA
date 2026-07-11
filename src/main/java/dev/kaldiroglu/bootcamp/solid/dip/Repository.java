package dev.kaldiroglu.bootcamp.solid.dip;
// ◀ Slides: Deck 04 SOLID — "Inject the Abstraction"

/** FIXED — the abstraction that high-level policy depends on. */
public interface Repository {

    void save(String item);

    int count();
}
