package dev.kaldiroglu.bootcamp.cleancode.honesterrors;
// ◀ Slides: Deck 03 Clean Code — "Handle Errors Honestly"

import java.util.Map;

/**
 * FIXED — fails fast and clearly. A missing user is an exceptional, named result,
 * thrown at the exact point the problem is known.
 */
public final class UserFinder {

    private final Map<String, String> users;

    public UserFinder(Map<String, String> users) {
        this.users = users;
    }

    public String find(String id) {
        String name = users.get(id);
        if (name == null) {
            throw new UserNotFoundException(id);
        }
        return name;
    }
}
