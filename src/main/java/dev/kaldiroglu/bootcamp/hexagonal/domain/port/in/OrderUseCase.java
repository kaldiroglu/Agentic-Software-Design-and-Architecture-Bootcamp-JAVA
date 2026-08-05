package dev.kaldiroglu.bootcamp.hexagonal.domain.port.in;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

import dev.kaldiroglu.bootcamp.hexagonal.domain.Order;

/**
 * A DRIVING port — "in" because the world drives the domain through it. It says what
 * the outside may ask of this domain: a driving adapter calls it, and the domain
 * implements it.
 *
 * <p>The domain owns this interface exactly as it owns the driven port next door in
 * {@code port.out}. The pair is the whole hexagon — in is how you reach the domain,
 * out is how the domain reaches you.
 *
 * <p>Both ports speak {@link Order}, never String. A port is domain vocabulary; the
 * moment it takes a primitive, the outside is free to hand it anything at all.
 *
 * <p>Cockburn names ports after intent; this one would be {@code ForPlacingOrders}.
 * We keep the plainer name so it reads beside the layered package.
 */
public interface OrderUseCase {

    void place(Order order);

    int placedCount();
}
