package dev.kaldiroglu.bootcamp.secure.sqlinjection;
// ◀ Slides: Deck 06 Secure Coding — "Never Build Queries by Concatenation"

/**
 * SMELL — builds SQL by string concatenation, so the user controls the query.
 * A crafted name turns the WHERE clause into an always-true condition.
 */
public final class VulnerableUserDao {

    public String buildQuery(String name) {
        return "SELECT * FROM users WHERE name = '" + name + "'";
    }
}
