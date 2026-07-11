package dev.kaldiroglu.bootcamp.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoanServiceTest {

    private static final LocalDate DUE = LocalDate.of(2026, 8, 1);

    private LoanService service;
    private LoanRepository loans;

    @BeforeEach
    void setUp() {
        Map<String, Book> catalogue = new HashMap<>();
        catalogue.put("isbn-dune", new Book("isbn-dune", "Dune", 2));
        catalogue.put("isbn-solo", new Book("isbn-solo", "Solo Copy", 1));
        loans = new InMemoryLoanRepository();
        service = new LoanService(catalogue, loans);
    }

    @Test
    void borrowingRecordsAnActiveLoan() {
        service.borrow("m1", "isbn-dune", DUE);
        assertEquals(1, loans.activeCountForMember("m1"));
        assertEquals(1, loans.copiesOnLoan("isbn-dune"));
    }

    @Test
    void cannotBorrowAnUnknownBook() {
        assertThrows(LoanExceptions.BookNotFound.class,
                () -> service.borrow("m1", "isbn-ghost", DUE));
    }

    @Test
    void cannotBorrowWhenNoCopiesRemain() {
        service.borrow("m1", "isbn-solo", DUE);   // the only copy
        assertThrows(LoanExceptions.NoCopiesAvailable.class,
                () -> service.borrow("m2", "isbn-solo", DUE));
    }

    @Test
    void enforcesTheFiveLoanLimit() {
        Map<String, Book> big = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            big.put("b" + i, new Book("b" + i, "Book " + i, 5));
        }
        var svc = new LoanService(big, new InMemoryLoanRepository());
        for (int i = 0; i < 5; i++) {
            svc.borrow("m1", "b" + i, DUE);
        }
        assertThrows(LoanExceptions.LoanLimitExceeded.class,
                () -> svc.borrow("m1", "b5", DUE));
    }

    @Test
    void returningFreesTheCopyForSomeoneElse() {
        service.borrow("m1", "isbn-solo", DUE);
        service.returnBook("m1", "isbn-solo");
        // The copy is free again, so another member can now borrow it.
        service.borrow("m2", "isbn-solo", DUE);
        assertEquals(1, loans.copiesOnLoan("isbn-solo"));
        assertEquals(0, loans.activeCountForMember("m1"));
    }
}
