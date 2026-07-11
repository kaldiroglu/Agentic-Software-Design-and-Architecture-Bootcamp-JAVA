package dev.kaldiroglu.bootcamp.solid.ocp;
// ◀ Slides: Deck 04 SOLID — "Add Behaviour by Adding Code"

/**
 * FIXED — the extension point. A new product kind is a new implementation of this
 * interface; the existing pricing code never changes.
 */
@FunctionalInterface
public interface Pricing {

    double price(double base);
}
