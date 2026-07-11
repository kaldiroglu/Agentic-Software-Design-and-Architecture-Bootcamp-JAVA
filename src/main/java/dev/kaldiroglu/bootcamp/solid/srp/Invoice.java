package dev.kaldiroglu.bootcamp.solid.srp;
// ◀ Slides: Deck 04 SOLID — "Split the Reasons to Change"

/** FIXED — the invoice owns just its own data and total. One reason to change. */
public record Invoice(String id, double amount) {

    public double total() {
        return amount;
    }
}
