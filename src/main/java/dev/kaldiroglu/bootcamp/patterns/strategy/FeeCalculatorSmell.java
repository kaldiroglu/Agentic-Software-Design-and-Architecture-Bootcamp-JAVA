package dev.kaldiroglu.bootcamp.patterns.strategy;
// ◀ Slides: Deck 05 Design Patterns — "Each Algorithm, Its Own Object"

/**
 * SMELL — the growing if/else. Each new payment kind edits this one method,
 * mixing every fee algorithm into a single place.
 */
public final class FeeCalculatorSmell {

    public double fee(String kind, double amount) {
        if (kind.equals("card")) {
            return amount * 0.02;
        }
        if (kind.equals("wire")) {
            return 5.0;
        }
        throw new IllegalArgumentException("Unknown kind: " + kind);
    }
}
