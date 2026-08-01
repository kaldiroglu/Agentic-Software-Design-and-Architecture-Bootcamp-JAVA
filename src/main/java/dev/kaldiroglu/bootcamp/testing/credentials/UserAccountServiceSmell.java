package dev.kaldiroglu.bootcamp.testing.credentials;
// ◀ Slides: Deck 08 Developer Testing — "Unit Test With Mocks" (the anemic version)

import dev.kaldiroglu.bootcamp.fundamentals.password.HashedPassword;
import dev.kaldiroglu.bootcamp.fundamentals.password.Password;
import dev.kaldiroglu.bootcamp.fundamentals.password.PasswordHasher;

import java.util.Optional;

/**
 * SMELL — anemic: {@link AnemicUser} is a bag of getters and setters, and the rules
 * live in this service, which must fetch the data before it can reason about it.
 *
 * <p>The cost shows up in the tests: to exercise a rule that has nothing to do with
 * storage, you must first stand up a repository double. Two collaborators must be
 * doubled instead of one — see {@code ChangePasswordTest}.
 */
public final class UserAccountServiceSmell {

    private final UserRepository users;
    private final PasswordHasher hasher;

    public UserAccountServiceSmell(UserRepository users, PasswordHasher hasher) {
        this.users = users;
        this.hasher = hasher;
    }

    public void changePassword(String username, Password current, Password next) {
        AnemicUser user = users.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Username not found: " + username));
        if (!hasher.verify(current, user.getHash())) {
            throw new UserAccount.OldPasswordMismatch();
        }
        if (current.equals(next)) {
            throw new UserAccount.SamePassword();
        }
        user.setHash(hasher.hash(next));
        users.save(user);
    }

    /** A data holder: all state, no behavior — the definition of an anemic model. */
    public static final class AnemicUser {

        private final String username;
        private HashedPassword hash;

        public AnemicUser(String username, HashedPassword hash) {
            this.username = username;
            this.hash = hash;
        }

        public String getUsername() {
            return username;
        }

        public HashedPassword getHash() {
            return hash;
        }

        public void setHash(HashedPassword hash) {
            this.hash = hash;
        }
    }

    public interface UserRepository {

        Optional<AnemicUser> findByUsername(String username);

        void save(AnemicUser user);
    }
}
