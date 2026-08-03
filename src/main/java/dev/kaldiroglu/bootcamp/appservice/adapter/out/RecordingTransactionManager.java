package dev.kaldiroglu.bootcamp.appservice.adapter.out;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

import dev.kaldiroglu.bootcamp.appservice.application.port.out.TransactionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A DRIVEN ADAPTER standing in for a real transaction manager. It does not manage a
 * transaction — it writes down that it was asked to, which is what lets a test assert
 * the thing the slides claim: one commit on success, a rollback on failure, and not a
 * word about either inside the domain.
 */
public final class RecordingTransactionManager implements TransactionManager {

    private final List<String> calls = new ArrayList<>();

    @Override
    public void begin() {
        calls.add("begin");
    }

    @Override
    public void commit() {
        calls.add("commit");
    }

    @Override
    public void rollback() {
        calls.add("rollback");
    }

    /** What happened, in order — the whole point of this adapter. */
    public List<String> calls() {
        return List.copyOf(calls);
    }
}
