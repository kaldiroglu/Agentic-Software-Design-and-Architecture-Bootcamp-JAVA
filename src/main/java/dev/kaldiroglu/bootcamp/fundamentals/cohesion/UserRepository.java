package dev.kaldiroglu.bootcamp.fundamentals.cohesion;
// ◀ Slides: Deck 02 Fundamentals — "Low vs High Cohesion"

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** FIXED — one job: storing and retrieving users. */
public final class UserRepository {

    private final Map<String, String> users = new HashMap<>();

    public void save(String id, String name) {
        users.put(id, name);
    }

    public Optional<String> findName(String id) {
        return Optional.ofNullable(users.get(id));
    }
}
