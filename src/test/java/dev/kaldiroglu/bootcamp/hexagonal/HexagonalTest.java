package dev.kaldiroglu.bootcamp.hexagonal;

import dev.kaldiroglu.bootcamp.hexagonal.adapter.InMemoryOrderRepository;
import dev.kaldiroglu.bootcamp.hexagonal.domain.OrderService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * We plug a driven adapter into the domain's port from outside. The domain is
 * tested with zero infrastructure knowledge — the whole point of the style.
 */
class HexagonalTest {

    @Test
    void adapterPlugsIntoTheDomainPort() {
        var service = new OrderService(new InMemoryOrderRepository());
        service.place("2x tea");
        service.place("1x cake");
        assertEquals(2, service.placedCount());
    }
}
