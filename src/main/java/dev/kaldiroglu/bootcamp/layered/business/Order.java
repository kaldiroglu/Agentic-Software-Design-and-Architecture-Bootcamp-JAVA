package dev.kaldiroglu.bootcamp.layered.business;
// ◀ Slides: Deck 07 Object Stereotypes — "No Primitives to the Domain"

/**
 * A WHOLE VALUE. The business layer never passes a bare {@code String} around: an
 * order is a thing with a name, and the name carries the rule that it may not be
 * blank. Ward Cunningham called this Whole Value in 1994; Kent Beck named its
 * absence Primitive Obsession.
 *
 * <p>The static factory is the only door. Once you hold an {@code Order} it is
 * valid, so nothing downstream has to check it again — parse, don't validate.
 */
public record Order(String text) {

    public static Order of(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("order must not be blank");
        }
        return new Order(text.strip());
    }
}
