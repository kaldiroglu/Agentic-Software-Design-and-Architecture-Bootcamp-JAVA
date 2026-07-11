package dev.kaldiroglu.bootcamp.secure.secrets;
// ◀ Slides: Deck 06 Secure Coding — "Keep Secrets Out of Code"

import java.util.Map;

/**
 * SMELL vs FIXED for handling secrets.
 * <p>The hard-coded key leaks into version control, logs and screenshots forever.
 * The fixed reader pulls the secret from external configuration at run time.
 */
public final class Secrets {

    /** SMELL — never do this. A real key committed to source. */
    public static final String HARD_CODED_API_KEY = "sk_live_do_not_commit_me";

    private final Map<String, String> config;

    /** FIXED — inject configuration (env vars / secret manager) from outside. */
    public Secrets(Map<String, String> config) {
        this.config = config;
    }

    public String apiKey() {
        String key = config.get("API_KEY");
        if (key == null || key.isBlank()) {
            throw new IllegalStateException("API_KEY is not configured");
        }
        return key;
    }
}
