package dev.kaldiroglu.bootcamp.stereotypes;
// ◀ Slides: Deck 07 Object Stereotypes — "The Identity Question"

import java.util.Objects;

/**
 * A value object. Two member ids with the same text are not merely equal — they are the
 * same id. There is no other "id 42", so identity is the value, and a record says so.
 */
public record MemberId(String value) {

    public MemberId {
        Objects.requireNonNull(value, "member id must not be null");
        String cleaned = value.strip();
        if (cleaned.isEmpty()) {
            throw new IllegalArgumentException("Member id must not be blank");
        }
        value = cleaned;
    }

    public static MemberId of(String value) {
        return new MemberId(value);
    }
}
