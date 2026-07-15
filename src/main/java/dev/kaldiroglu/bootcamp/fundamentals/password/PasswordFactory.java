package dev.kaldiroglu.bootcamp.fundamentals.password;
// ◀ Slides: Deck 02 Fundamentals — "Only Functional Cohesion Scales"

/**
 * FIXED — the construction task, split out of {@link Password}. As ways to build a
 * password multiply (temporary, reset token, from a raw input, ...), they live here
 * as named strategies. Each reuses {@code Password}'s own rules, so validation and
 * generation stay in one place.
 */
public final class PasswordFactory {

    private static final int TEMPORARY_LENGTH = 16;
    private static final int RESET_TOKEN_LENGTH = 24;

    /** A short-lived password to hand a user on first login. */
    public Password temporary() {
        return Password.random(TEMPORARY_LENGTH);
    }

    /** A longer one-time token for a password-reset flow. */
    public Password resetToken() {
        return Password.random(RESET_TOKEN_LENGTH);
    }

    /** Wrap a raw input as a Password, enforcing the policy. */
    public Password fromRaw(String raw) {
        return Password.of(raw);
    }
}
