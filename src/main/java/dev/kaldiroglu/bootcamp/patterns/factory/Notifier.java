package dev.kaldiroglu.bootcamp.patterns.factory;
// ◀ Slides: Deck 05 Design Patterns — "Centralise Creation"

/** The product interface callers depend on — never a concrete channel. */
public interface Notifier {

    String send(String message);
}
