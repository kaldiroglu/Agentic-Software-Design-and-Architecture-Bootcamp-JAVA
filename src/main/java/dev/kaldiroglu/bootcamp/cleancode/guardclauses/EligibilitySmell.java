package dev.kaldiroglu.bootcamp.cleancode.guardclauses;
// ◀ Slides: Deck 03 Clean Code — guard clauses

/**
 * SMELL — deep nesting ("the arrow anti-pattern"). The happy path is buried at the
 * bottom and you must track every brace to follow the logic.
 */
public final class EligibilitySmell {

    public String describe(int age, boolean member, boolean banned) {
        if (!banned) {
            if (member) {
                if (age >= 18) {
                    return "eligible";
                } else {
                    return "too young";
                }
            } else {
                return "not a member";
            }
        } else {
            return "banned";
        }
    }
}
