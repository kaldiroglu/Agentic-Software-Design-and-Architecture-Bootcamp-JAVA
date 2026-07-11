package dev.kaldiroglu.bootcamp.solid.dip;
// ◀ Slides: Deck 04 SOLID — "Inject the Abstraction"

/**
 * SMELL — high-level policy creates its own low-level detail with {@code new}.
 * It is welded to one storage technology and cannot be tested with a fake.
 */
public final class OrderServiceSmell {

    private final InMemoryRepository repo = new InMemoryRepository();

    public void place(String order) {
        repo.save(order);
    }

    public int placedCount() {
        return repo.count();
    }
}
