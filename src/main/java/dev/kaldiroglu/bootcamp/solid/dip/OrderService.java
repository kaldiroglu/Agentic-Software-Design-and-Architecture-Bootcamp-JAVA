package dev.kaldiroglu.bootcamp.solid.dip;
// ◀ Slides: Deck 04 SOLID — "Inject the Abstraction"

/**
 * FIXED — the service depends on the {@link Repository} abstraction, injected from
 * outside. The concrete store is chosen by the caller, not hard-wired here.
 */
public final class OrderService {

    private final Repository repo;

    public OrderService(Repository repo) {
        this.repo = repo;
    }

    public void place(String order) {
        repo.save(order);
    }

    public int placedCount() {
        return repo.count();
    }
}
