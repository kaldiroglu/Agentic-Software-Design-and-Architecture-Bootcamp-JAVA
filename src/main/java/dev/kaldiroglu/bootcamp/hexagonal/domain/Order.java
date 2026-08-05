package dev.kaldiroglu.bootcamp.hexagonal.domain;
// ◀ Slides: Deck 07 Object Stereotypes — "No Primitives to the Domain"

/**
 * A WHOLE VALUE, and the only thing the ports speak. The domain is handed an
 * {@code Order}, never a String: an order is a thing with a name, and the name
 * carries the rule that it may not be blank.
 *
 * <p>The static factory is the only door, so holding an {@code Order} is proof it
 * is valid — parse, don't validate. Compare {@code appservice.domain.Order}, which
 * is the same value object one rung up the ladder.
 */
public record Order(String text) {

    public static Order of(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("order must not be blank");
        }
        return new Order(text.strip());
    }
}
