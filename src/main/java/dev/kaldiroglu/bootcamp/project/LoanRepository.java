package dev.kaldiroglu.bootcamp.project;
// ◀ Slides: Deck 09 SW Design Project with AI — "A Library Loan Service"

/**
 * The port the service depends on (DIP). The service never knows whether loans
 * live in memory, in Postgres, or in a fake for tests.
 */
public interface LoanRepository {

    void save(Loan loan);

    /** Removes one active loan of the book by the member; true if one existed. */
    boolean remove(String isbn, String memberId);

    /** How many books this member currently has on loan. */
    int activeCountForMember(String memberId);

    /** How many copies of this book are currently on loan. */
    int copiesOnLoan(String isbn);
}
