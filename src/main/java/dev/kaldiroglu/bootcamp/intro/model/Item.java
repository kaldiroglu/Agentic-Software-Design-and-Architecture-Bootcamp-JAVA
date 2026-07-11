package dev.kaldiroglu.bootcamp.intro.model;
// ◀ Slides: Deck 01 Introduction — "Same Feature, Two Designs"

/** A single order line: a product at a price, bought in some quantity. */
public record Item(String name, double price, int quantity) {

    public double lineTotal() {
        return price * quantity;
    }
}
