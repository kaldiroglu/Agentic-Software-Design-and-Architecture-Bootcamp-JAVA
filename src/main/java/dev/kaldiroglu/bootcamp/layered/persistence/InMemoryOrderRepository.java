package dev.kaldiroglu.bootcamp.layered.persistence;
// ◀ Slides: Deck 11 Layered Architecture — "A Request, Layer by Layer"

import java.util.ArrayList;
import java.util.List;

/** Persistence layer: the database (here, a list). */
public final class InMemoryOrderRepository implements OrderRepository {

    private final List<String> orders = new ArrayList<>();

    @Override
    public void save(String order) {
        orders.add(order);
    }

    @Override
    public int count() {
        return orders.size();
    }
}
