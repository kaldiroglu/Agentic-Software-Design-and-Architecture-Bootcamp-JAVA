package dev.kaldiroglu.bootcamp.stereotypes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("A domain service is the last resort, not the first")
class AnemicModelTest {

    private static final MemberId AYSE = MemberId.of("ayse");
    private static final LocalDate DUE = LocalDate.of(2026, 3, 15);
    private static final LocalDate THREE_DAYS_LATE = LocalDate.of(2026, 3, 18);

    private final LoanRules rules = new LoanRules();

    @Test
    @DisplayName("Both models give the same answer — the smell is not a bug")
    void bothAnswerTheSameQuestion() {
        AnemicLoan anemic = new AnemicLoan(AYSE, DUE);
        Loan rich = new Loan(AYSE, DUE);

        assertEquals(rules.isOverdue(anemic, THREE_DAYS_LATE), rich.isOverdueOn(THREE_DAYS_LATE));
        assertEquals(rules.daysLate(anemic, THREE_DAYS_LATE), rich.daysLateOn(THREE_DAYS_LATE));
    }

    @Test
    @DisplayName("The rich entity answers for itself — no service, no getter")
    void theEntityAnswersForItself() {
        Loan loan = new Loan(AYSE, DUE);

        assertTrue(loan.isOverdueOn(THREE_DAYS_LATE));
        assertEquals(3, loan.daysLateOn(THREE_DAYS_LATE));
    }

    @Test
    @DisplayName("On time is not late, in either model")
    void onTimeIsNotLate() {
        assertEquals(0, new Loan(AYSE, DUE).daysLateOn(DUE));
        assertEquals(0, rules.daysLate(new AnemicLoan(AYSE, DUE), DUE));
    }
}
