package dev.kaldiroglu.bootcamp.patterns.strategy;
// ◀ Slides: Deck 05 Design Patterns — "Each Algorithm, Its Own Object"

/** The strategy: one interface, many interchangeable fee algorithms. */
@FunctionalInterface
public interface Fee {

    double of(double amount);

    Fee CARD = amount -> amount * 0.02;   // 2%
    Fee WIRE = amount -> 5.0;             // flat
}
