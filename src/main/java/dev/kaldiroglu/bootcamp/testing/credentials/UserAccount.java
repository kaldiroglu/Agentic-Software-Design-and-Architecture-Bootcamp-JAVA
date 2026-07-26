package dev.kaldiroglu.bootcamp.testing.credentials;
// ◀ Slides: Deck 07 Developer Testing — "Move the Rules, Lose the Mocks"

import dev.kaldiroglu.bootcamp.fundamentals.password.HashedPassword;
import dev.kaldiroglu.bootcamp.fundamentals.password.Password;
import dev.kaldiroglu.bootcamp.fundamentals.password.PasswordHasher;

/**
 * FIXED — the rule lives on the entity that owns the data.
 *
 * <p>Because {@code changePassword} needs nothing but the account itself and a
 * hasher, a test can exercise it directly: no repository, no service, no mocking
 * library. Compare {@link UserAccountServiceSmell}, where the same rule sits in a
 * service and therefore drags a repository double into every test.
 *
 * <p>Note what is <em>absent</em>: there is no password-policy check here.
 * {@link Password#of(String)} already rejected an invalid one, so by the time a
 * {@code Password} exists it is valid by construction (Deck 02, Deck 06).
 */
public final class UserAccount {

    private final String username;
    private HashedPassword hash;

    public UserAccount(String username, HashedPassword hash) {
        this.username = username;
        this.hash = hash;
    }

    public String username() {
        return username;
    }

    public HashedPassword hash() {
        return hash;
    }

    /**
     * The rule: the current password must match, and the next one must differ.
     * Hashing itself stays behind the {@link PasswordHasher} port (DIP).
     */
    public void changePassword(Password current, Password next, PasswordHasher hasher) {
        if (!hasher.verify(current, hash)) {
            throw new OldPasswordMismatch();
        }
        if (current.equals(next)) {
            throw new SamePassword();
        }
        this.hash = hasher.hash(next);
    }

    public static final class OldPasswordMismatch extends RuntimeException {
        public OldPasswordMismatch() {
            super("Current password does not match");
        }
    }

    public static final class SamePassword extends RuntimeException {
        public SamePassword() {
            super("New password must differ from the current one");
        }
    }
}
