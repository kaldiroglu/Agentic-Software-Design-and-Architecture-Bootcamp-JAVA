package dev.kaldiroglu.bootcamp.layered.persistence;
// ◀ Slides: Deck 12 Layered Architecture — "A Request, Layer by Layer"

import dev.kaldiroglu.bootcamp.layered.business.Order;

import java.util.ArrayList;
import java.util.List;

/** Persistence layer: the database (here, a list). */
public final class InMemoryOrderRepository implements OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public int count() {
        return orders.size();
    }
}
