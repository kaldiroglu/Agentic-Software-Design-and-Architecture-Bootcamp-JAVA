package dev.kaldiroglu.bootcamp.hexagonal.domain;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

/**
 * An INPUT PORT (a driving port) — what the outside world may ask this domain to do.
 * The domain owns this interface too, exactly as it owns {@link OrderRepository}; the
 * difference is direction. A driving adapter calls this port; the domain implements it.
 * A driven port is the mirror image: the domain calls it, an adapter implements it.
 *
 * <p>Cockburn names ports after intent — this one would be {@code ForPlacingOrders}.
 * We keep the plainer name so it reads beside the layered package.
 */
public interface OrderUseCase {

    void place(String order);

    int placedCount();
}
