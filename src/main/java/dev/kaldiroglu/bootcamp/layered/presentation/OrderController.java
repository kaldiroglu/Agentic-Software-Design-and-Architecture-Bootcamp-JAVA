package dev.kaldiroglu.bootcamp.layered.presentation;
// ◀ Slides: Deck 12 Layered Architecture — "A Request, Layer by Layer"

import dev.kaldiroglu.bootcamp.layered.business.Order;
import dev.kaldiroglu.bootcamp.layered.business.OrderService;

/**
 * Presentation layer. It depends on the business layer, which depends on
 * persistence — dependencies point strictly downward, never up or skipping.
 *
 * <p>This is where the primitive dies: a request arrives as text, {@code Order.of}
 * turns it into a whole value, and nothing below this line ever sees a raw String.
 */
public final class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    /** Handles a "request" and returns a status, like an HTTP endpoint would. */
    public String place(String text) {
        try {
            service.place(Order.of(text));
            return "201 Created";
        } catch (IllegalArgumentException e) {
            return "400 Bad Request";
        }
    }
}
