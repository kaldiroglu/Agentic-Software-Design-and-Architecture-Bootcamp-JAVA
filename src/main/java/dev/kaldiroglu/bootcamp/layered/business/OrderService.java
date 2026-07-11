package dev.kaldiroglu.bootcamp.layered.business;
// ◀ Slides: Deck 11 Layered Architecture — "A Request, Layer by Layer"

import dev.kaldiroglu.bootcamp.layered.persistence.OrderRepository;

/**
 * Business layer. Note the direction of the dependency: this domain logic imports
 * the persistence layer below it. That downward coupling is the classic layered
 * trade-off — the domain knows about infrastructure. (Topic 12 inverts it.)
 */
public final class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void place(String order) {
        if (order == null || order.isBlank()) {
            throw new IllegalArgumentException("order must not be blank");
        }
        repository.save(order);
    }

    public int placedCount() {
        return repository.count();
    }
}
