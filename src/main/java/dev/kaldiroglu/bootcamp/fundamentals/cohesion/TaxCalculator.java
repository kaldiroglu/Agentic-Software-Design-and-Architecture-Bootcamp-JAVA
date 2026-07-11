package dev.kaldiroglu.bootcamp.fundamentals.cohesion;
// ◀ Slides: Deck 02 Fundamentals — "Low vs High Cohesion"

/** FIXED — one job: computing tax. Its single reason to change is a tax-rule change. */
public final class TaxCalculator {

    private final double rate;

    public TaxCalculator(double rate) {
        this.rate = rate;
    }

    public double taxOn(double amount) {
        return amount * rate;
    }
}
