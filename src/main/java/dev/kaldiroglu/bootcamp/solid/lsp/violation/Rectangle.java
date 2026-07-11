package dev.kaldiroglu.bootcamp.solid.lsp.violation;
// ◀ Slides: Deck 04 SOLID — "When 'is-a' Lies"

/** A rectangle whose width and height vary independently. */
public class Rectangle {

    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int area() {
        return width * height;
    }
}
