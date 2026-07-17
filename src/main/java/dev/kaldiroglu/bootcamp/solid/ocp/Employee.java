package dev.kaldiroglu.bootcamp.solid.ocp;
// ◀ Slides: Deck 04 SOLID — "The Type-Field Smell"

/**
 * FIXED — the extension point. Each role is a subclass that knows its own pay
 * rule; a new role is a new subclass and this code never changes. Open for
 * extension, closed for modification.
 */
public abstract class Employee {

    protected final double base;

    protected Employee(double base) {
        this.base = base;
    }

    public abstract double pay();
}
