package dev.kaldiroglu.bootcamp.solid.ocp;
// ◀ Slides: Deck 04 SOLID — "The Type-Field Smell"

public final class Manager extends Employee {

    public Manager(double base) {
        super(base);
    }

    @Override
    public double pay() {
        return base + base * 0.20;   // + bonus
    }
}
