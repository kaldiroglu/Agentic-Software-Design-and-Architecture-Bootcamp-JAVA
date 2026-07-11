package dev.kaldiroglu.bootcamp.fundamentals.demeter;
// ◀ Slides: Deck 02 Fundamentals — "The 'Train Wreck' / Tell, Don't Ask"

public record Order(String id, Customer customer) {

    /**
     * FIXED — the order exposes what callers actually want and hides its internal
     * shape. Callers depend on Order alone, not on Customer and Address as well.
     */
    public String shippingCity() {
        return customer.shippingCity();
    }
}
