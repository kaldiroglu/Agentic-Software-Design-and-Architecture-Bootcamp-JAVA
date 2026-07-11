package dev.kaldiroglu.bootcamp.fundamentals.demeter;
// ◀ Slides: Deck 02 Fundamentals — "The 'Train Wreck' / Tell, Don't Ask"

public record Customer(String name, Address address) {

    /** Tell-don't-ask: the customer answers about its own parts. */
    public String shippingCity() {
        return address.city();
    }
}
