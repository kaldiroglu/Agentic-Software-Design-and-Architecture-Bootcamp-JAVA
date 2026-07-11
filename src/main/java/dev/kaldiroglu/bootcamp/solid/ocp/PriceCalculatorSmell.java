package dev.kaldiroglu.bootcamp.solid.ocp;
// ◀ Slides: Deck 04 SOLID — "Add Behaviour by Adding Code"

/**
 * SMELL — closed to extension. Every new product kind forces an edit to this
 * method (and a re-test of everything it already did).
 */
public final class PriceCalculatorSmell {

    public double price(String kind, double base) {
        if (kind.equals("book")) {
            return base;                 // books untaxed
        }
        if (kind.equals("food")) {
            return base * 1.08;          // food +8%
        }
        throw new IllegalArgumentException("Unknown kind: " + kind);
    }
}
