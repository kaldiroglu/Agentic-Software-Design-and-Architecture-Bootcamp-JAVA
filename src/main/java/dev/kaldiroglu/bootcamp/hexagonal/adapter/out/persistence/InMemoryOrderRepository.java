package dev.kaldiroglu.bootcamp.hexagonal.adapter.out.persistence;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "The Domain Owns the Interface"

import dev.kaldiroglu.bootcamp.hexagonal.application.port.out.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A DRIVEN ADAPTER — "out" because the application drives it, "persistence" because
 * that is the technology it speaks. It imports the application to implement the
 * application's port, so the dependency points INWARD. Swap this for a Postgres
 * adapter and nothing inside notices.
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
