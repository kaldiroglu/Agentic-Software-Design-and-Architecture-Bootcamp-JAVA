package dev.kaldiroglu.bootcamp.stereotypes;
// ◀ Slides: Deck 07 Object Stereotypes — "Where Objects Live"  (the smell)

import java.time.LocalDate;

/** The smell: fields and nothing else. Every question about a loan is asked elsewhere. */
public record AnemicLoan(MemberId memberId, LocalDate dueOn) {
}
