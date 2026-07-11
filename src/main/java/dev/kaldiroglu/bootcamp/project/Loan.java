package dev.kaldiroglu.bootcamp.project;
// ◀ Slides: Deck 09 SW Design Project with AI — "A Library Loan Service"

import java.time.LocalDate;

/** One member's active loan of one book copy, due back on a date. */
public record Loan(String isbn, String memberId, LocalDate dueDate) {
}
