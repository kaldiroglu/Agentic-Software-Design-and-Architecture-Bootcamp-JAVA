package dev.kaldiroglu.bootcamp.solid.lsp.fixed;
// ◀ Slides: Deck 04 SOLID — "When 'is-a' Lies"

/**
 * FIXED — no false "is-a". Rectangle and Square are both Shapes that can report
 * their area; neither pretends to be substitutable for the other.
 */
public interface Shape {

    double area();
}
