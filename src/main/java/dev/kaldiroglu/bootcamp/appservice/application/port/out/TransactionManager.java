package dev.kaldiroglu.bootcamp.appservice.application.port.out;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

/**
 * A DRIVEN port for a mechanism, not a rule — which is exactly why it is declared here
 * and not in {@code domain}. A transaction is something the application needs in order
 * to run a use case safely; it is not something the business would recognize.
 *
 * <p>Ask a domain expert what a rollback is and you get a blank look. That is a decent
 * test for whether a concept belongs in the model.
 */
public interface TransactionManager {

    void begin();

    void commit();

    void rollback();
}
