package dev.kaldiroglu.bootcamp.hexagonal.application.port.out;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "In and Out, All the Way Down"

/**
 * A DRIVEN port — "out" because the application drives the world through it. The
 * application declares what it needs; the outside must adapt to it. Nothing in this
 * package names a technology, and nothing here could.
 *
 * <p>The mirror of {@code port.in}: same owner, opposite direction.
 */
public interface OrderRepository {

    void save(String order);

    int count();
}
