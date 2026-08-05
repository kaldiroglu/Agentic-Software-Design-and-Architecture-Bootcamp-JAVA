package dev.kaldiroglu.bootcamp.hexagonal.domain;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

import dev.kaldiroglu.bootcamp.hexagonal.domain.port.in.OrderUseCase;
import dev.kaldiroglu.bootcamp.hexagonal.domain.port.out.OrderRepository;

/**
 * The domain. It implements the driving port and calls the driven one, and it names
 * no technology at all — the only imports are its own ports.
 *
 * <p>There is no validation here: {@link Order} cannot exist in an invalid state, so
 * by the time one arrives the rule has already been enforced.
 */
public final class OrderService implements OrderUseCase {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void place(Order order) {
        repository.save(order);
    }

    @Override
    public int placedCount() {
        return repository.count();
    }
}
