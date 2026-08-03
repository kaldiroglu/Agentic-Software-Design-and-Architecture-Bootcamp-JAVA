package dev.kaldiroglu.bootcamp.hexagonal.adapter.in;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

import dev.kaldiroglu.bootcamp.hexagonal.domain.port.in.OrderUseCase;

/**
 * A DRIVING ADAPTER at the edge — "in" because it drives the domain. It speaks HTTP
 * on one side and the domain's input port on the other, and that is its whole job:
 * translate a request into a call, and a domain error into a status code.
 *
 * <p>Compare the import above with the layered package's controller, which imports
 * {@code OrderService} — a concrete class from the layer below. Here the only domain
 * name in this file is a port. Nothing in this package knows an OrderService exists,
 * so the domain can be re-implemented without touching a line of it.
 */
public final class OrderController {

    private final OrderUseCase orders;

    public OrderController(OrderUseCase orders) {
        this.orders = orders;
    }

    /** Handles a "request" and returns a status, like an HTTP endpoint would. */
    public String place(String order) {
        try {
            orders.place(order);
            return "201 Created";
        } catch (IllegalArgumentException e) {
            return "400 Bad Request";
        }
    }

    /** Protocol details — status codes, response bodies — stay out here at the edge. */
    public String count() {
        return "200 OK: " + orders.placedCount();
    }
}
