package dev.kaldiroglu.bootcamp.hexagonal.domain;
// ◀ Slides: Deck 12 Hexagonal / Onion / Clean — "The Domain Owns the Interface"

/**
 * Domain logic at the centre. It depends only on the {@link OrderRepository} port —
 * defined right here in the domain. Unlike the layered version, the domain has NO
 * dependency on any persistence package. Infrastructure depends on the domain, not
 * the other way round.
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
