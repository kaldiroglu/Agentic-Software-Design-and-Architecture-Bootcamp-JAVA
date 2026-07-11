package dev.kaldiroglu.bootcamp.cleancode.honesterrors;
// ◀ Slides: Deck 03 Clean Code — "Handle Errors Honestly"

import java.util.Map;

/**
 * SMELL — swallows the miss and returns {@code null}. The failure is hidden until
 * some distant caller dereferences the null and gets a mysterious crash far from
 * the real cause.
 */
public final class UserFinderSmell {

    private final Map<String, String> users;

    public UserFinderSmell(Map<String, String> users) {
        this.users = users;
    }

    public String find(String id) {
        try {
            String name = users.get(id);
            return name;            // null when absent — the caller can't tell why
        } catch (RuntimeException e) {
            return null;            // and here we even swallow real errors
        }
    }
}
