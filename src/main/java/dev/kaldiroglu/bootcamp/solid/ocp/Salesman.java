package dev.kaldiroglu.bootcamp.solid.ocp;
// ◀ Slides: Deck 04 SOLID — "The Type-Field Smell"

public final class Salesman extends Employee {

    public Salesman(double base) {
        super(base);
    }

    @Override
    public double pay() {
        return base + base * 0.10;   // + commission
    }
}
