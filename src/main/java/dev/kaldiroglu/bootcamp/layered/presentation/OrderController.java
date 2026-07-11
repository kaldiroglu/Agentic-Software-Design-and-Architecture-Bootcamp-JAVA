package dev.kaldiroglu.bootcamp.layered.presentation;
// ◀ Slides: Deck 11 Layered Architecture — "A Request, Layer by Layer"

import dev.kaldiroglu.bootcamp.layered.business.OrderService;

/**
 * Presentation layer. It depends on the business layer, which depends on
 * persistence — dependencies point strictly downward, never up or skipping.
 */
public final class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    /** Handles a "request" and returns a status, like an HTTP endpoint would. */
    public String place(String order) {
        try {
            service.place(order);
            return "201 Created";
        } catch (IllegalArgumentException e) {
            return "400 Bad Request";
        }
    }
}
