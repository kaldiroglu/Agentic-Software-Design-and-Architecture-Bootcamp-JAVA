package dev.kaldiroglu.bootcamp.appservice.application.port.in;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

/**
 * A DRIVING port. Note where it lives: {@code application}, not {@code domain}. A use
 * case is specific to this application; the {@code Order} rule it runs would outlive
 * any number of them. That difference is the whole reason for the extra package.
 */
public interface PlaceOrderUseCase {

    void place(String text);

    int placedCount();
}
