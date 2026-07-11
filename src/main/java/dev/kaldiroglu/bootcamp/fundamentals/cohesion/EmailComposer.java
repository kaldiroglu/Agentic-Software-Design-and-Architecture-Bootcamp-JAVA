package dev.kaldiroglu.bootcamp.fundamentals.cohesion;
// ◀ Slides: Deck 02 Fundamentals — "Low vs High Cohesion"

/** FIXED — one job: composing an e-mail message. */
public final class EmailComposer {

    public String compose(String to, String subject, String body) {
        return "To: %s%nSubject: %s%n%n%s".formatted(to, subject, body);
    }
}
