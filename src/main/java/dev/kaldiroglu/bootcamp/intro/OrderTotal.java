package dev.kaldiroglu.bootcamp.intro;
// ◀ Slides: Deck 01 Introduction — "Same Feature, Two Designs"

import dev.kaldiroglu.bootcamp.intro.model.Order;

/**
 * FIXED — totalling now does one thing: sum the lines, apply whichever
 * {@link Discount} it was given, then add tax. The discount rule can change
 * (VIP, seasonal, none) without touching this class.
 */
public final class OrderTotal {

    private static final double TAX_RATE = 0.20;

    private final Discount discount;

    public OrderTotal(Discount discount) {
        this.discount = discount;
    }

    public double total(Order order) {
        double discounted = discount.apply(order.subtotal(), order.customer());
        return discounted * (1 + TAX_RATE);
    }
}
