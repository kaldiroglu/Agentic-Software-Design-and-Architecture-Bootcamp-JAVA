package dev.kaldiroglu.bootcamp.stereotypes;
// ◀ Slides: Deck 07 Object Stereotypes — "Where Objects Live"  (the smell)

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * A domain service holding rules that belong to the loan itself.
 *
 * Notice what every method starts with: reaching into the object for its due date. That
 * is feature envy, and it is what an anemic model always produces — the data in one
 * place, the rules that use it in another, and a getter between them.
 */
public final class LoanRules {

    public boolean isOverdue(AnemicLoan loan, LocalDate on) {
        return on.isAfter(loan.dueOn());
    }

    public long daysLate(AnemicLoan loan, LocalDate on) {
        return isOverdue(loan, on) ? ChronoUnit.DAYS.between(loan.dueOn(), on) : 0;
    }
}
