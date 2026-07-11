package dev.kaldiroglu.bootcamp.solid.ocp;
// ◀ Slides: Deck 04 SOLID — "Add Behaviour by Adding Code"

/**
 * FIXED — concrete pricing rules. Add {@code ElectronicsPricing} tomorrow without
 * touching any of these or the calculator that uses them.
 */
public final class PricingRules {

    private PricingRules() {
    }

    public static final Pricing BOOK = base -> base;            // untaxed
    public static final Pricing FOOD = base -> base * 1.08;     // +8%
}
