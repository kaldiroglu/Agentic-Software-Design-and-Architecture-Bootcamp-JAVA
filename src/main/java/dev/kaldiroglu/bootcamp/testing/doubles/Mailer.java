package dev.kaldiroglu.bootcamp.testing.doubles;
// ◀ Slides: Deck 07 Developer Testing — "Test Doubles Isolate the Unit"

/**
 * A collaborator we don't want to really invoke in a unit test. Because the code
 * depends on this interface (DIP), a test can substitute a double for it.
 */
public interface Mailer {

    void send(String to, String body);
}
