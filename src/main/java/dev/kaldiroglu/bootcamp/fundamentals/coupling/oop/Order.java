package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;
// ◀ Slides: Deck 02 Fundamentals — "Coupling in OOP: Subtyping & Message"

/** A tiny order that knows how to describe its own receipt. */
public record Order(String id) {

    public String receipt() {
        return "Receipt for order " + id;
    }
}
