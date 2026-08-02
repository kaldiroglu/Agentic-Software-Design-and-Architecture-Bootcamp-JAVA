package dev.kaldiroglu.bootcamp.hexagonal.application.domain.service;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "The Domain Owns the Interface"

import dev.kaldiroglu.bootcamp.hexagonal.application.port.in.OrderUseCase;
import dev.kaldiroglu.bootcamp.hexagonal.application.port.out.OrderRepository;

/**
 * The center, sitting between the two ports the application owns. It IMPLEMENTS the
 * driving port {@link OrderUseCase} — that is how the outside reaches in — and it
 * DEPENDS ON the driven port {@link OrderRepository} — that is how it reaches out.
 * Both imports point at {@code application.port}; neither points at an adapter, and
 * that is the rule this whole package tree exists to make visible.
 *
 * <p>Its sibling package {@code application.domain.model} would hold the entities.
 * This example has none — an order is still a String — so the package is absent
 * rather than empty. Compare the Library Loan Service, where the model is real.
 */
public final class OrderService implements OrderUseCase {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void place(String order) {
        if (order == null || order.isBlank()) {
            throw new IllegalArgumentException("order must not be blank");
        }
        repository.save(order);
    }

    @Override
    public int placedCount() {
        return repository.count();
    }
}
