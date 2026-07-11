package dev.kaldiroglu.bootcamp.solid.lsp.fixed;
// ◀ Slides: Deck 04 SOLID — "When 'is-a' Lies"

/** FIXED — a square is its own Shape with a single side, not a crippled rectangle. */
public record Square(int side) implements Shape {

    @Override
    public double area() {
        return (double) side * side;
    }
}
