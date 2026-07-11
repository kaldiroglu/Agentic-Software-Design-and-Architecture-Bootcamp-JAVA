package dev.kaldiroglu.bootcamp.intro.model;
// ◀ Slides: Deck 01 Introduction — "Same Feature, Two Designs"

import java.util.List;

/** An order: the items bought and the customer who bought them. */
public record Order(List<Item> items, Customer customer) {

    /** The raw sum of the lines, before any discount or tax. */
    public double subtotal() {
        return items.stream().mapToDouble(Item::lineTotal).sum();
    }
}
