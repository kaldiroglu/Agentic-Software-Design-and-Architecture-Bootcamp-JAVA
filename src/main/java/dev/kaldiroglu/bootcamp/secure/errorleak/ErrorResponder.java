package dev.kaldiroglu.bootcamp.secure.errorleak;
// ◀ Slides: Deck 06 Secure Coding — "Don't Leak in Error Messages"

/**
 * Two ways to answer a failed request. The smell hands internal detail to the
 * user; the safe version logs detail privately and returns a generic message.
 */
public final class ErrorResponder {

    /** SMELL — leaks schema, stack traces and versions to the caller. */
    public String smellUserMessage(Exception e) {
        return "Error: " + e.getMessage();
    }

    /** FIXED — the caller sees nothing sensitive. */
    public String safeUserMessage(Exception e) {
        return "Something went wrong. Please try again.";
    }

    /** FIXED — the real detail goes to the server log only. */
    public String internalLog(Exception e) {
        return "[ERROR] " + e.getClass().getSimpleName() + ": " + e.getMessage();
    }
}
