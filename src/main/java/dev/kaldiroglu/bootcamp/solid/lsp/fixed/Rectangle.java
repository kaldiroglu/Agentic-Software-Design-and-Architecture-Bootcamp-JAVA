package dev.kaldiroglu.bootcamp.solid.lsp.fixed;
// ◀ Slides: Deck 04 SOLID — "When 'is-a' Lies"

/** FIXED — an immutable rectangle. Width and height are independent, as promised. */
public record Rectangle(int width, int height) implements Shape {

    @Override
    public double area() {
        return (double) width * height;
    }
}
