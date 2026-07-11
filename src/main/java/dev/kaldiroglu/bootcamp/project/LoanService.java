package dev.kaldiroglu.bootcamp.project;
// ◀ Slides: Deck 09 SW Design Project with AI — "A Library Loan Service"

import java.time.LocalDate;
import java.util.Map;

/**
 * The core use case: borrowing and returning books, with the library's rules.
 * It depends only on the {@link LoanRepository} port and a read-only catalogue —
 * high cohesion (loan rules in one place), low coupling (no storage details here).
 */
public final class LoanService {

    static final int MAX_LOANS_PER_MEMBER = 5;

    private final Map<String, Book> catalogue;
    private final LoanRepository loans;

    public LoanService(Map<String, Book> catalogue, LoanRepository loans) {
        this.catalogue = Map.copyOf(catalogue);
        this.loans = loans;
    }

    public Loan borrow(String memberId, String isbn, LocalDate dueDate) {
        Book book = catalogue.get(isbn);
        if (book == null) {
            throw new LoanExceptions.BookNotFound(isbn);
        }
        if (loans.activeCountForMember(memberId) >= MAX_LOANS_PER_MEMBER) {
            throw new LoanExceptions.LoanLimitExceeded(memberId, MAX_LOANS_PER_MEMBER);
        }
        if (loans.copiesOnLoan(isbn) >= book.totalCopies()) {
            throw new LoanExceptions.NoCopiesAvailable(isbn);
        }
        Loan loan = new Loan(isbn, memberId, dueDate);
        loans.save(loan);
        return loan;
    }

    public void returnBook(String memberId, String isbn) {
        loans.remove(isbn, memberId);
    }
}
