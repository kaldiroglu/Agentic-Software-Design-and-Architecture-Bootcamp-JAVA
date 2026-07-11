package dev.kaldiroglu.bootcamp.testing;
// ◀ Slides: Deck 07 Developer Testing — "Arrange · Act · Assert"

/** A tiny unit under test — used to demonstrate the Arrange-Act-Assert shape. */
public final class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("division by zero");
        }
        return a / b;
    }
}
