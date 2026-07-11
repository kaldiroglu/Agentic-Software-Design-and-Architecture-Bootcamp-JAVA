package dev.kaldiroglu.bootcamp.fundamentals.demeter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Both approaches return the same city. The difference is who KNOWS the structure:
 * the train wreck knows all three classes; the fixed caller knows only Order.
 */
class DemeterTest {

    private Order sampleOrder() {
        return new Order("o1", new Customer("Ada", new Address("1 Main St", "Istanbul")));
    }

    @Test
    void bothReturnTheSameCity() {
        Order order = sampleOrder();
        assertEquals("Istanbul", new ShippingLabelSmell().cityFor(order));
        assertEquals("Istanbul", order.shippingCity());
    }
}
