package dev.kaldiroglu.bootcamp.intro.model;
// ◀ Slides: Deck 01 Introduction — "Same Feature, Two Designs"

/** A customer placing an order. {@code vip} customers may earn a discount. */
public record Customer(String name, boolean vip) {
}
