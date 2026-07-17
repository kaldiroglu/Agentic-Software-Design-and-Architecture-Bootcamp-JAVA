package dev.kaldiroglu.bootcamp.solid.ocp;
// ◀ Slides: Deck 04 SOLID — "The Type-Field Smell"

public final class Engineer extends Employee {

    public Engineer(double base) {
        super(base);
    }

    @Override
    public double pay() {
        return base;
    }
}
