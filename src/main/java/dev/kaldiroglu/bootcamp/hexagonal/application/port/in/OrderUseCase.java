package dev.kaldiroglu.bootcamp.hexagonal.application.port.in;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "In and Out, All the Way Down"

/**
 * A DRIVING port — "in" because the world drives the application through it. It says
 * what the outside may ask: a driving adapter calls it, and a service implements it.
 *
 * <p>Ports live under {@code application}, not {@code domain}, because they are
 * application-specific — they describe what THIS app offers and needs. Entities in
 * {@code application.domain} would outlive any one of them.
 *
 * <p>Cockburn names ports after intent; this one would be {@code ForPlacingOrders}.
 * We keep the plainer name so it reads beside the layered package.
 */
public interface OrderUseCase {

    void place(String order);

    int placedCount();
}
