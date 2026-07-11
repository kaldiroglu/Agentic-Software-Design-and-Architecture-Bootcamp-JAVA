package dev.kaldiroglu.bootcamp.secure.sqlinjection;
// ◀ Slides: Deck 06 Secure Coding — "Never Build Queries by Concatenation"

import java.util.List;

/**
 * FIXED — a fixed query template with the name bound as a parameter. Whatever the
 * user types is treated as a value to match, never as SQL to execute.
 */
public final class SafeUserDao {

    private static final String SQL = "SELECT * FROM users WHERE name = ?";

    public ParameterizedQuery buildQuery(String name) {
        return new ParameterizedQuery(SQL, List.of(name));
    }
}
