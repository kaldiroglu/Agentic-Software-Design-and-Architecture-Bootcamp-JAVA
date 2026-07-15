package dev.kaldiroglu.bootcamp.fundamentals.password;
// ◀ Slides: Deck 02 Fundamentals — "Only Functional Cohesion Scales"

/**
 * FIXED — the presentation task, split out of {@link Password}. As display formats
 * multiply (log redaction, UI hints, ...), they live here instead of bloating the
 * value object. One task: turning a Password into a safe display string.
 */
public final class PasswordFormatter {

    private static final String BULLET = "•";

    /** Log-safe: reveals only the length, never the value. */
    public String redactedForLog(Password password) {
        return "[redacted: " + password.reveal().length() + " chars]";
    }

    /** Show only the first {@code shown} characters (e.g. a UI hint); mask the rest. */
    public String showingFirst(Password password, int shown) {
        String value = password.reveal();
        int visible = Math.max(0, Math.min(shown, value.length()));
        return value.substring(0, visible) + BULLET.repeat(value.length() - visible);
    }
}
