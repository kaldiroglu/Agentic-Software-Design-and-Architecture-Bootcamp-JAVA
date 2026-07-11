package dev.kaldiroglu.bootcamp.intro;
// ◀ Slides: Deck 01 Introduction — "Same Feature, Two Designs"

import dev.kaldiroglu.bootcamp.intro.model.Customer;

/**
 * FIXED — the discount decision, pulled out behind a small boundary.
 * The totalling code no longer knows which rule applies; new rules are new
 * implementations, not edits to the calculation.
 */
@FunctionalInterface
public interface Discount {

    /** Returns the amount after applying this discount to {@code subtotal}. */
    double apply(double subtotal, Customer customer);

    /** No discount at all. */
    static Discount none() {
        return (subtotal, customer) -> subtotal;
    }

    /** Ten percent off for VIP customers, nothing for the rest. */
    static Discount vip() {
        return (subtotal, customer) -> customer.vip() ? subtotal * 0.9 : subtotal;
    }
}
