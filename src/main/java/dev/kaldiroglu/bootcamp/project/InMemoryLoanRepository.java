package dev.kaldiroglu.bootcamp.project;
// ◀ Slides: Deck 09 SW Design Project with AI — "A Library Loan Service"

import java.util.ArrayList;
import java.util.List;

/** A simple in-memory adapter for the {@link LoanRepository} port. */
public final class InMemoryLoanRepository implements LoanRepository {

    private final List<Loan> loans = new ArrayList<>();

    @Override
    public void save(Loan loan) {
        loans.add(loan);
    }

    @Override
    public boolean remove(String isbn, String memberId) {
        return loans.removeIf(l -> l.isbn().equals(isbn) && l.memberId().equals(memberId));
    }

    @Override
    public int activeCountForMember(String memberId) {
        return (int) loans.stream().filter(l -> l.memberId().equals(memberId)).count();
    }

    @Override
    public int copiesOnLoan(String isbn) {
        return (int) loans.stream().filter(l -> l.isbn().equals(isbn)).count();
    }
}
