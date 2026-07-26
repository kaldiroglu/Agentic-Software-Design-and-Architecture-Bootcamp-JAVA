package dev.kaldiroglu.bootcamp.testing.credentials;

import dev.kaldiroglu.bootcamp.fundamentals.password.HashedPassword;
import dev.kaldiroglu.bootcamp.fundamentals.password.Password;
import dev.kaldiroglu.bootcamp.fundamentals.password.PasswordHasher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The same rule, tested against both designs — so the cost of the anemic version is
 * visible rather than argued. This project uses no mocking library on purpose: the
 * doubles below are hand-written, which makes the number of them impossible to hide.
 */
class ChangePasswordTest {

    private static final Password CURRENT = Password.of("Correct-horse1!");
    private static final Password NEXT = Password.of("Battery-staple2!");
    private static final Password WRONG = Password.of("Wrong-password9!");

    private final PasswordHasher hasher = new FakeHasher();

    @Nested
    @DisplayName("anemic — rules in the service")
    class AnemicVersion {

        @Test
        @DisplayName("needs a repository double for a rule that is not about storage")
        void needsARepositoryDouble() {
            var stored = new UserAccountServiceSmell.AnemicUser("ada", hasher.hash(CURRENT));
            var repository = new FakeUserRepository(stored);          // double #1
            var service = new UserAccountServiceSmell(repository, hasher);  // double #2: the hasher

            assertThrows(UserAccount.OldPasswordMismatch.class,
                    () -> service.changePassword("ada", WRONG, NEXT));
            assertEquals(0, repository.saves, "nothing may be persisted when the rule rejects");
        }
    }

    @Nested
    @DisplayName("rich — rules on the entity")
    class RichVersion {

        @Test
        @DisplayName("the same rule, with no repository in sight")
        void needsNoRepository() {
            var account = new UserAccount("ada", hasher.hash(CURRENT));

            assertThrows(UserAccount.OldPasswordMismatch.class,
                    () -> account.changePassword(WRONG, NEXT, hasher));
        }

        @Test
        void refusesToReuseTheCurrentPassword() {
            var account = new UserAccount("ada", hasher.hash(CURRENT));

            assertThrows(UserAccount.SamePassword.class,
                    () -> account.changePassword(CURRENT, CURRENT, hasher));
        }

        @Test
        void replacesTheHashWhenTheCurrentPasswordMatches() {
            var account = new UserAccount("ada", hasher.hash(CURRENT));

            account.changePassword(CURRENT, NEXT, hasher);

            assertTrue(hasher.verify(NEXT, account.hash()));
        }
    }

    /** A stand-in for the real PBKDF2 adapter: deterministic, and not a real hash. */
    private static final class FakeHasher implements PasswordHasher {

        @Override
        public HashedPassword hash(Password password) {
            return new HashedPassword("ENC:" + password.hashCode());
        }

        @Override
        public boolean verify(Password candidate, HashedPassword stored) {
            return stored.encoded().equals("ENC:" + candidate.hashCode());
        }
    }

    /** Only the anemic design needs this one. */
    private static final class FakeUserRepository implements UserAccountServiceSmell.UserRepository {

        private final UserAccountServiceSmell.AnemicUser user;
        private int saves;

        FakeUserRepository(UserAccountServiceSmell.AnemicUser user) {
            this.user = user;
        }

        @Override
        public Optional<UserAccountServiceSmell.AnemicUser> findByUsername(String username) {
            return user.getUsername().equals(username) ? Optional.of(user) : Optional.empty();
        }

        @Override
        public void save(UserAccountServiceSmell.AnemicUser user) {
            saves++;
        }
    }
}
