package dev.kaldiroglu.bootcamp.patterns.proxy;
// ◀ Slides: Deck 05 Design Patterns — "Same Interface, Extra Control"

/** The interface shared by the real object and its proxy — callers can't tell them apart. */
public interface Image {

    String render();
}
