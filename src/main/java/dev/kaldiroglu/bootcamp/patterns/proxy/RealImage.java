package dev.kaldiroglu.bootcamp.patterns.proxy;
// ◀ Slides: Deck 05 Design Patterns — "Same Interface, Extra Control"

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The real, heavyweight object: constructing it "loads from disk" (here, bumps a
 * counter so tests can observe when the load happens).
 */
public final class RealImage implements Image {

    private final String path;

    public RealImage(String path, AtomicInteger loads) {
        this.path = path;
        loads.incrementAndGet();   // the expensive work happens on construction
    }

    @Override
    public String render() {
        return "rendered: " + path;
    }
}
