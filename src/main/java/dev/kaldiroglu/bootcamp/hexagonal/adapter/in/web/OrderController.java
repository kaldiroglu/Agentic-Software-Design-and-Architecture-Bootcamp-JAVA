package dev.kaldiroglu.bootcamp.hexagonal.adapter.in.web;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "The Adapter Really Is Adapter"

import dev.kaldiroglu.bootcamp.hexagonal.application.port.in.OrderUseCase;

/**
 * A DRIVING ADAPTER — "in" because it drives the application, "web" because that is
 * the technology it speaks. It translates a request into a port call, and a domain
 * error into a status code. That is its whole job.
 *
 * <p>The import above is the lesson: it names a port, where the layered controller
 * names {@code OrderService}, a concrete class from the layer below. Nothing in this
 * package knows an OrderService exists.
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
