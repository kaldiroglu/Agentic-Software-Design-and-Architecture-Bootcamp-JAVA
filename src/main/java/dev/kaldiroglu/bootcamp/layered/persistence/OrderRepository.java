package dev.kaldiroglu.bootcamp.layered.persistence;
// ◀ Slides: Deck 12 Layered Architecture — "A Request, Layer by Layer"

import dev.kaldiroglu.bootcamp.layered.business.Order;

/** Persistence layer: the data-access contract. It stores whole Orders, not Strings. */
public interface OrderRepository {

    void save(Order order);

    int count();
}
