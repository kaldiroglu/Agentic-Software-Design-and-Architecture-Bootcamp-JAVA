package dev.kaldiroglu.bootcamp.hexagonal.domain;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "The Domain Owns the Interface"

/**
 * Domain logic at the center, sitting between two ports it owns. It IMPLEMENTS the
 * driving port {@link OrderUseCase} — that is how the outside reaches in — and it
 * DEPENDS ON the driven port {@link OrderRepository} — that is how it reaches out.
 * Unlike the layered version, the domain has NO dependency on any persistence or
 * presentation package. Infrastructure depends on the domain, not the other way round.
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
