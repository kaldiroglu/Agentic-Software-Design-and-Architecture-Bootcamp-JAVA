package dev.kaldiroglu.bootcamp.solid.dip;
// ◀ Slides: Deck 04 SOLID — "Inject the Abstraction"

import java.util.ArrayList;
import java.util.List;

/** FIXED — one concrete detail that depends on the abstraction, not the reverse. */
public final class InMemoryRepository implements Repository {

    private final List<String> items = new ArrayList<>();

    @Override
    public void save(String item) {
        items.add(item);
    }

    @Override
    public int count() {
        return items.size();
    }
}
