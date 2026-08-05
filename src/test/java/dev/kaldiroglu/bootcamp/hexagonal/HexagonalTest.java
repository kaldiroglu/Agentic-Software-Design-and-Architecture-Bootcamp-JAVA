package dev.kaldiroglu.bootcamp.hexagonal;

import dev.kaldiroglu.bootcamp.hexagonal.adapter.in.OrderController;
import dev.kaldiroglu.bootcamp.hexagonal.adapter.out.InMemoryOrderRepository;
import dev.kaldiroglu.bootcamp.hexagonal.domain.Order;
import dev.kaldiroglu.bootcamp.hexagonal.domain.OrderService;
import dev.kaldiroglu.bootcamp.hexagonal.domain.port.in.OrderUseCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Adapters plug into the domain's ports from outside — a driving one on the way in,
 * a driven one on the way out. The domain is tested with zero infrastructure
 * knowledge, which is the whole point of the style.
 */
class HexagonalTest {

    /** The composition root: the only place that knows which adapters are in play. */
    private OrderController wire() {
        return new OrderController(new OrderService(new InMemoryOrderRepository()));
    }

    @Test
    void drivenAdapterPlugsIntoTheDomainPort() {
        var service = new OrderService(new InMemoryOrderRepository());
        service.place(Order.of("2x tea"));
        service.place(Order.of("1x cake"));
        assertEquals(2, service.placedCount());
    }

    @Test
    void aRequestEntersThroughTheDrivingAdapterAndReachesTheDrivenOne() {
        var controller = wire();
        assertEquals("201 Created", controller.place("2x coffee"));
        assertEquals("200 OK: 1", controller.count());
    }

    @Test
    void theDrivingAdapterTurnsADomainErrorIntoAStatusCode() {
        assertEquals("400 Bad Request", wire().place("  "));
    }

    /**
     * The controller is built on a hand-written stand-in for the port — no
     * OrderService anywhere. If the driving adapter had reached past the port to the
     * class behind it, this would not compile.
     */
    @Test
    void theDrivingAdapterNeedsThePortOnly() {
        List<Order> received = new ArrayList<>();
        OrderUseCase standIn = new OrderUseCase() {
            @Override
            public void place(Order order) {
                received.add(order);
            }

            @Override
            public int placedCount() {
                return received.size();
            }
        };

        var controller = new OrderController(standIn);
        assertEquals("201 Created", controller.place("1x tea"));
        assertEquals(List.of(Order.of("1x tea")), received);
    }

    /**
     * No repository, no service, no adapter — the blank-order rule is enforced by the
     * value object alone. That is what "the domain owns it" means at the type level:
     * an invalid Order cannot be constructed, so nothing downstream may assume one.
     */
    @Test
    void anInvalidOrderCannotBeConstructed() {
        assertThrows(IllegalArgumentException.class, () -> Order.of("  "));
        assertThrows(IllegalArgumentException.class, () -> Order.of(null));
        assertEquals("2x tea", Order.of("  2x tea  ").text());
    }
}
