package dev.kaldiroglu.bootcamp.layered.persistence;
// ◀ Slides: Deck 12 Layered Architecture — "A Request, Layer by Layer"

/** Persistence layer: the data-access contract. */
public interface OrderRepository {

    void save(String order);

    int count();
}
