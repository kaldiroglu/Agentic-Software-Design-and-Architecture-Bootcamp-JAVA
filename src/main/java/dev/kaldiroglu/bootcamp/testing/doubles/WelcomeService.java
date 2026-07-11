package dev.kaldiroglu.bootcamp.testing.doubles;
// ◀ Slides: Deck 07 Developer Testing — "Test Doubles Isolate the Unit"

/** The unit under test: it greets a new member by sending them a welcome mail. */
public final class WelcomeService {

    private final Mailer mailer;

    public WelcomeService(Mailer mailer) {
        this.mailer = mailer;
    }

    public void welcome(String email, String name) {
        mailer.send(email, "Welcome, " + name + "!");
    }
}
