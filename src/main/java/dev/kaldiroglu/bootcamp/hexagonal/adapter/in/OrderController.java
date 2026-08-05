package dev.kaldiroglu.bootcamp.hexagonal.adapter.in;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

import dev.kaldiroglu.bootcamp.hexagonal.domain.Order;
import dev.kaldiroglu.bootcamp.hexagonal.domain.port.in.OrderUseCase;

/**
 * A DRIVING ADAPTER at the edge — "in" because it drives the domain. It speaks HTTP
 * on one side and the domain's input port on the other, and that is its whole job:
 * translate a request into a call, and a domain error into a status code.
 *
 * <p>Translating includes the type. Text arrives from the wire and {@code Order.of}
 * turns it into a whole value here, at the edge — the domain is never handed a raw
 * String, so it never has to ask whether one is any good.
 *
 * <p>Compare the imports with the layered package's controller, which imports
 * {@code OrderService} — a concrete class from the layer below. Here the only domain
 * names in this file are a port and a value object.
 */
public final class OrderController {

    private final OrderUseCase orders;

    public OrderController(OrderUseCase orders) {
        this.orders = orders;
    }

    /** Handles a "request" and returns a status, like an HTTP endpoint would. */
    public String place(String text) {
        try {
            orders.place(Order.of(text));
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
