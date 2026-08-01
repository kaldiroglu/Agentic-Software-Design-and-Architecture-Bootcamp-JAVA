package dev.kaldiroglu.bootcamp.stereotypes;
// ◀ Slides: Deck 07 Object Stereotypes — "Where Objects Live"  (the fix)

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * The fix: the rules live with the data they need.
 *
 * A domain service is the last resort, not the first. Nothing here needs to be asked for
 * its due date, because nothing outside needs to know a due date exists.
 */
public final class Loan {

    private final MemberId memberId;
    private final LocalDate dueOn;

    public Loan(MemberId memberId, LocalDate dueOn) {
        this.memberId = Objects.requireNonNull(memberId, "member id must not be null");
        this.dueOn = Objects.requireNonNull(dueOn, "due date must not be null");
    }

    public boolean isOverdueOn(LocalDate date) {
        return date.isAfter(dueOn);
    }

    public long daysLateOn(LocalDate date) {
        return isOverdueOn(date) ? ChronoUnit.DAYS.between(dueOn, date) : 0;
    }

    public MemberId memberId() {
        return memberId;
    }
}
