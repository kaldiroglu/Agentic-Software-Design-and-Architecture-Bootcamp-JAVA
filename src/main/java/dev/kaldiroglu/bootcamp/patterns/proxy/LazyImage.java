package dev.kaldiroglu.bootcamp.patterns.proxy;
// ◀ Slides: Deck 05 Design Patterns — "Same Interface, Extra Control"

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A virtual proxy: same {@link Image} interface, but the real image is not loaded
 * until the first {@code render()} — and only once thereafter.
 */
public final class LazyImage implements Image {

    private final String path;
    private final AtomicInteger loads;
    private RealImage real;   // created on demand

    public LazyImage(String path, AtomicInteger loads) {
        this.path = path;
        this.loads = loads;
    }

    @Override
    public String render() {
        if (real == null) {
            real = new RealImage(path, loads);   // load on first use
        }
        return real.render();
    }
}
