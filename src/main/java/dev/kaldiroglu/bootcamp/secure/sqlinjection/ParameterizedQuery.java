package dev.kaldiroglu.bootcamp.secure.sqlinjection;
// ◀ Slides: Deck 06 Secure Coding — "Never Build Queries by Concatenation"

import java.util.List;

/**
 * A fixed SQL template plus bound parameters. The user's text never enters the SQL
 * string — the driver sends it separately, as data, so it can't change the query.
 */
public record ParameterizedQuery(String sql, List<String> parameters) {

    public ParameterizedQuery {
        parameters = List.copyOf(parameters);
    }
}
