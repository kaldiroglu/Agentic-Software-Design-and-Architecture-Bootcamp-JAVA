package dev.kaldiroglu.bootcamp.appservice.adapter.in;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "The Adapter Really Is Adapter"

import dev.kaldiroglu.bootcamp.appservice.application.port.in.PlaceOrderUseCase;

/** A DRIVING ADAPTER. It knows a port and a protocol, and nothing else. */
public final class OrderController {

    private final PlaceOrderUseCase orders;

    public OrderController(PlaceOrderUseCase orders) {
        this.orders = orders;
    }

    public String place(String text) {
        try {
            orders.place(text);
            return "201 Created";
        } catch (IllegalArgumentException e) {
            return "400 Bad Request";
        }
    }

    public String count() {
        return "200 OK: " + orders.placedCount();
    }
}
