package dev.kaldiroglu.bootcamp.project;
// ◀ Slides: Deck 09 SW Design Project with AI — "A Library Loan Service"

/** Specific, honest failures for the loan rules — never silent nulls. */
public final class LoanExceptions {

    private LoanExceptions() {
    }

    public static final class BookNotFound extends RuntimeException {
        public BookNotFound(String isbn) {
            super("No book with isbn: " + isbn);
        }
    }

    public static final class LoanLimitExceeded extends RuntimeException {
        public LoanLimitExceeded(String memberId, int limit) {
            super("Member " + memberId + " already holds the maximum of " + limit + " loans");
        }
    }

    public static final class NoCopiesAvailable extends RuntimeException {
        public NoCopiesAvailable(String isbn) {
            super("No copies available for isbn: " + isbn);
        }
    }
}
