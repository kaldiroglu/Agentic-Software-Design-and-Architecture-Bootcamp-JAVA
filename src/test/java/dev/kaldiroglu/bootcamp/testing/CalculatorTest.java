package dev.kaldiroglu.bootcamp.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Demonstrates the Arrange-Act-Assert shape and an error-path test. */
class CalculatorTest {

    @Test
    @DisplayName("adds two numbers (arrange, act, assert)")
    void addsTwoNumbers() {
        Calculator calc = new Calculator();   // arrange
        int result = calc.add(2, 3);          // act
        assertEquals(5, result);              // assert
    }

    @Test
    @DisplayName("dividing by zero is an error, not a wrong answer")
    void divideByZeroThrows() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> calc.divide(1, 0));
    }
}
