package dev.kaldiroglu.bootcamp.hexagonal.domain;
// ◀ Slides: Deck 12 Hexagonal / Onion / Clean — "The Domain Owns the Interface"

/**
 * A PORT — an interface owned by the domain. The domain declares what it needs;
 * the outside world must adapt to it. Notice this package imports no infrastructure.
 */
public interface OrderRepository {

    void save(String order);

    int count();
}
