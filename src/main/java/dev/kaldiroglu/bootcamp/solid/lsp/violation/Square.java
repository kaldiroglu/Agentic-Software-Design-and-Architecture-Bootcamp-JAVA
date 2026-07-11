package dev.kaldiroglu.bootcamp.solid.lsp.violation;
// ◀ Slides: Deck 04 SOLID — "When 'is-a' Lies"

/**
 * SMELL — a Square "is-a" Rectangle only in maths, not in behaviour. To stay
 * square it must break the Rectangle contract: setting width also changes height.
 * Any code written against Rectangle now gets surprising results.
 */
public class Square extends Rectangle {

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;   // silently overrides the caller's height
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}
