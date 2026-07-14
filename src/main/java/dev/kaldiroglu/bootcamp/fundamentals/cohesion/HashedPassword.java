package dev.kaldiroglu.bootcamp.fundamentals.cohesion;
// ◀ Slides: Deck 02 Fundamentals — "A Password Value Object" (presenter note)

/**
 * The DURABLE form of a password — what actually gets persisted. It is a distinct
 * value object from {@link Password} because it has a different lifecycle: a
 * {@code Password} lives for milliseconds, a {@code HashedPassword} outlives every
 * session. The encoded string carries everything needed to verify later
 * (algorithm parameters, salt, and the derived key).
 */
public record HashedPassword(String encoded) {
}
