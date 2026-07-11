package dev.kaldiroglu.bootcamp.cleancode.guardclauses;
// ◀ Slides: Deck 03 Clean Code — guard clauses

/**
 * FIXED — guard clauses handle each disqualifying case up front and return early.
 * The happy path is left un-nested at the end, easy to read at a glance.
 */
public final class Eligibility {

    public String describe(int age, boolean member, boolean banned) {
        if (banned) {
            return "banned";
        }
        if (!member) {
            return "not a member";
        }
        if (age < 18) {
            return "too young";
        }
        return "eligible";
    }
}
