package dev.kaldiroglu.bootcamp.fundamentals.cohesion;
// ◀ Slides: Deck 02 Fundamentals — "A Password Value Object" (presenter note)

/**
 * The answer to "why isn't hashing on {@link Password}?" — hashing lives HERE, in a
 * port, not in the value object.
 *
 * <p>It changes for different reasons than a password does (algorithm, work factor,
 * compliance) and needs infrastructure (a crypto library), so putting it on the
 * pure domain value object would break cohesion and invert no dependencies. This
 * interface is the abstraction; an adapter such as {@code Pbkdf2PasswordHasher}
 * implements it (Dependency Inversion Principle).
 */
public interface PasswordHasher {

    /** Derive the durable, storable form of a live password. */
    HashedPassword hash(Password password);

    /** Verify a freshly-entered password against a previously stored hash. */
    boolean verify(Password candidate, HashedPassword stored);
}
