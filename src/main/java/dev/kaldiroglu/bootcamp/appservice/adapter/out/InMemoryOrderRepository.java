package dev.kaldiroglu.bootcamp.appservice.adapter.out;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

import dev.kaldiroglu.bootcamp.appservice.application.port.out.OrderRepository;
import dev.kaldiroglu.bootcamp.appservice.domain.Order;

import java.util.ArrayList;
import java.util.List;

/** A DRIVEN ADAPTER for storage. */
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
