package dev.kaldiroglu.bootcamp.appservice;

import dev.kaldiroglu.bootcamp.appservice.adapter.in.OrderController;
import dev.kaldiroglu.bootcamp.appservice.adapter.out.InMemoryOrderRepository;
import dev.kaldiroglu.bootcamp.appservice.adapter.out.RecordingTransactionManager;
import dev.kaldiroglu.bootcamp.appservice.application.PlaceOrderService;
import dev.kaldiroglu.bootcamp.appservice.application.port.in.PlaceOrderUseCase;
import dev.kaldiroglu.bootcamp.appservice.domain.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The same order domain as {@code hexagonal}, with an application service added to own
 * the transaction. These tests exist to make one claim checkable: the mechanism lives
 * in the application layer, and the domain has never heard of it.
 */
class AppServiceTest {

    private final RecordingTransactionManager transactions = new RecordingTransactionManager();
    private final InMemoryOrderRepository repository = new InMemoryOrderRepository();
    private final PlaceOrderService service = new PlaceOrderService(repository, transactions);

    @Test
    void aValidOrderCommitsExactlyOnce() {
        service.place("2x coffee");

        assertEquals(List.of("begin", "commit"), transactions.calls());
        assertEquals(1, service.placedCount());
    }

    @Test
    void aBlankOrderRollsBackAndNeverCommits() {
        assertThrows(IllegalArgumentException.class, () -> service.place("  "));

        assertEquals(List.of("begin", "rollback"), transactions.calls());
        assertEquals(0, service.placedCount());
    }

    /**
     * The rule, on its own. No repository, no transaction, no service — the domain
     * needs none of them to be exercised, which is what "pure" buys you.
     */
    @Test
    void theDomainRuleNeedsNoPortsAtAll() {
        assertEquals("2x tea", Order.of("  2x tea  ").text());
        assertThrows(IllegalArgumentException.class, () -> Order.of(""));
    }

    @Test
    void aRequestEntersThroughTheControllerAndIsCounted() {
        var controller = new OrderController(service);

        assertEquals("201 Created", controller.place("1x cake"));
        assertEquals("400 Bad Request", controller.place("  "));
        assertEquals("200 OK: 1", controller.count());
    }

    /**
     * The controller drives a hand-written stand-in for the input port. No service, no
     * repository, no transaction manager in sight: the mechanism is owned one layer in,
     * and nothing at the edge needs to know it exists.
     */
    @Test
    void theEdgeKnowsNothingOfTheTransaction() {
        var received = new java.util.ArrayList<String>();
        PlaceOrderUseCase standIn = new PlaceOrderUseCase() {
            @Override
            public void place(String text) {
                received.add(text);
            }

            @Override
            public int placedCount() {
                return received.size();
            }
        };

        assertEquals("201 Created", new OrderController(standIn).place("1x tea"));
        assertEquals(List.of("1x tea"), received);
    }
}
