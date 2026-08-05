package dev.kaldiroglu.bootcamp.hexagonal.domain.port.out;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "The Domain Owns the Interface"

import dev.kaldiroglu.bootcamp.hexagonal.domain.Order;

/**
 * A DRIVEN port — "out" because the domain drives the world through it. The domain
 * declares what it needs; the outside must adapt to it. Notice this package imports
 * no infrastructure, and could not: nothing here names a database.
 *
 * <p>It stores whole {@link Order}s. An adapter may flatten one to columns or JSON,
 * but that is the adapter's business — the port stays in domain vocabulary.
 *
 * <p>The mirror of {@code port.in}. Same owner, opposite direction — the domain calls
 * this one, and an adapter implements it.
 */
public interface OrderRepository {

    void save(Order order);

    int count();
}
