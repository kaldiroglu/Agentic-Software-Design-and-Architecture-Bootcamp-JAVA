package dev.kaldiroglu.bootcamp.solid.ocp;
// ◀ Slides: Deck 04 SOLID — "The Type-Field Smell"

/**
 * SMELL — one class, an int {@code type} field, and a method that branches on it.
 * Every new role forces an edit here (and to every other method that switches on
 * type). Closed to extension in exactly the way OCP warns against.
 */
public final class EmployeePaySmell {

    public static final int ENGINEER = 1;
    public static final int SALESMAN = 2;
    public static final int MANAGER = 3;

    private final int type;
    private final double base;

    public EmployeePaySmell(int type, double base) {
        this.type = type;
        this.base = base;
    }

    public double pay() {
        if (type == ENGINEER) {
            return base;
        }
        if (type == SALESMAN) {
            return base + base * 0.10;   // + commission
        }
        if (type == MANAGER) {
            return base + base * 0.20;   // + bonus
        }
        throw new IllegalStateException("Unknown type: " + type);
    }
}
