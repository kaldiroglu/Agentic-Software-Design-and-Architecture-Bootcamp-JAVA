package dev.kaldiroglu.bootcamp.appservice.application;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

import dev.kaldiroglu.bootcamp.appservice.application.port.in.PlaceOrderUseCase;
import dev.kaldiroglu.bootcamp.appservice.application.port.out.OrderRepository;
import dev.kaldiroglu.bootcamp.appservice.application.port.out.TransactionManager;
import dev.kaldiroglu.bootcamp.appservice.domain.Order;

/**
 * The APPLICATION SERVICE. It owns the transaction and coordinates the work; it makes
 * no business decision of its own. Read the body: every line is either a mechanism
 * (begin, commit, rollback) or a delegation ({@code Order.of}, {@code save}). There is
 * no {@code if} about orders here — and the day one appears, a rule has leaked out of
 * the domain.
 *
 * <p>This is Evans' Application layer and Onion's outer core ring, in a dozen lines.
 */
public final class PlaceOrderService implements PlaceOrderUseCase {

    private final OrderRepository repository;
    private final TransactionManager transactions;

    public PlaceOrderService(OrderRepository repository, TransactionManager transactions) {
        this.repository = repository;
        this.transactions = transactions;
    }

    @Override
    public void place(String text) {
        transactions.begin();
        try {
            repository.save(Order.of(text));
            transactions.commit();
        } catch (RuntimeException e) {
            transactions.rollback();
            throw e;
        }
    }

    @Override
    public int placedCount() {
        return repository.count();
    }
}
