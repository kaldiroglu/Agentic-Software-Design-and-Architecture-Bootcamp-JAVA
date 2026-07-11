package dev.kaldiroglu.bootcamp.cleancode.honesterrors;
// ◀ Slides: Deck 03 Clean Code — "Handle Errors Honestly"

/** A clear, specific failure — far more useful than a silent {@code null}. */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("No user with id: " + id);
    }
}
