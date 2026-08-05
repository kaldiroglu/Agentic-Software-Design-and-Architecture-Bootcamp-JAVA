package dev.kaldiroglu.bootcamp.layered.business;
// ◀ Slides: Deck 12 Layered Architecture — "A Request, Layer by Layer"

import dev.kaldiroglu.bootcamp.layered.persistence.OrderRepository;

/**
 * Business layer. Note the direction of the dependency: this domain logic imports
 * the persistence layer below it. That downward coupling is the classic layered
 * trade-off — the domain knows about infrastructure. (Topic 13 inverts it.)
 *
 * <p>It takes an {@link Order}, not a String. The blank-order rule lives in the
 * value object, so there is no validation left to do here.
 */
public final class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void place(Order order) {
        repository.save(order);
    }

    public int placedCount() {
        return repository.count();
    }
}
