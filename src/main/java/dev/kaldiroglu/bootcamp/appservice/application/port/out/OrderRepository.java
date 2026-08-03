package dev.kaldiroglu.bootcamp.appservice.application.port.out;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

import dev.kaldiroglu.bootcamp.appservice.domain.Order;

/** A DRIVEN port — the application declares what it needs of storage. */
public interface OrderRepository {

    void save(Order order);

    int count();
}
