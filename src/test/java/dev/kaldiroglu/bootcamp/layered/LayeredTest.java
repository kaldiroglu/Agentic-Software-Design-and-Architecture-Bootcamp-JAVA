package dev.kaldiroglu.bootcamp.layered;

import dev.kaldiroglu.bootcamp.layered.business.Order;
import dev.kaldiroglu.bootcamp.layered.business.OrderService;
import dev.kaldiroglu.bootcamp.layered.persistence.InMemoryOrderRepository;
import dev.kaldiroglu.bootcamp.layered.presentation.OrderController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** A request flows down the layers (controller → service → repository) and back. */
class LayeredTest {

    private OrderController wire() {
        var repo = new InMemoryOrderRepository();
        var service = new OrderService(repo);
        return new OrderController(service);
    }

    @Test
    void validRequestFlowsDownAndPersists() {
        assertEquals("201 Created", wire().place("2x coffee"));
    }

    @Test
    void invalidRequestIsRejectedAtTheTop() {
        assertEquals("400 Bad Request", wire().place("  "));
    }

    /** The rule lives in the value object, so it needs no layer at all to run. */
    @Test
    void theBlankRuleBelongsToTheValueObject() {
        assertThrows(IllegalArgumentException.class, () -> Order.of("  "));
        assertEquals("2x coffee", Order.of("  2x coffee  ").text());
    }
}
