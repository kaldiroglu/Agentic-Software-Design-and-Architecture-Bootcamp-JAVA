package dev.kaldiroglu.bootcamp.hexagonal.adapter.out;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "The Domain Owns the Interface"

import dev.kaldiroglu.bootcamp.hexagonal.domain.port.out.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A DRIVEN ADAPTER at the edge — "out" because the domain drives it, not the other
 * way round. It imports the domain to implement the domain's port, so the dependency
 * points INWARD. Swap this for a Postgres adapter and the domain never notices.
 */
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
