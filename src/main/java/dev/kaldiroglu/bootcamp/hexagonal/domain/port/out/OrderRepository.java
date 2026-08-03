package dev.kaldiroglu.bootcamp.hexagonal.domain.port.out;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "The Domain Owns the Interface"

/**
 * A DRIVEN port — "out" because the domain drives the world through it. The domain
 * declares what it needs; the outside must adapt to it. Notice this package imports
 * no infrastructure, and could not: nothing here names a database.
 *
 * <p>The mirror of {@code port.in}. Same owner, opposite direction — the domain calls
 * this one, and an adapter implements it.
 */
public interface OrderRepository {

    void save(String order);

    int count();
}
