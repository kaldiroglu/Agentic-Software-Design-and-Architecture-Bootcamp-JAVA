package dev.kaldiroglu.bootcamp.appservice.domain;
// ◀ Slides: Deck 13 Hexagonal / Onion / Clean — "Ports and Adapters"

/**
 * The domain, whole. Compare the {@code hexagonal} package, where OrderService held
 * the rule AND reached for a repository: this type holds the rule and nothing else.
 * It names no port, no transaction, no technology — there is nothing here for
 * infrastructure to hide behind, because infrastructure never reaches this far in.
 */
public record Order(String text) {

    public static Order of(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("order must not be blank");
        }
        return new Order(text.strip());
    }
}
