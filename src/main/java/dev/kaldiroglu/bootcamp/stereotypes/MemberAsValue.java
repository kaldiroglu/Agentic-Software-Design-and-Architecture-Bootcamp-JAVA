package dev.kaldiroglu.bootcamp.stereotypes;
// ◀ Slides: Deck 07 Object Stereotypes — "The Identity Question"  (the smell)

/**
 * The smell: an entity written as a record.
 *
 * A member is an entity — Ayşe is Ayşe whether she is Standard or Premium. But a record
 * is equal by all its components, so upgrading her tier produces an object the program no
 * longer recognizes as the same member. The type says "value"; the domain means "entity".
 */
public record MemberAsValue(MemberId id, String name, Tier tier) {

    public MemberAsValue movedTo(Tier newTier) {
        return new MemberAsValue(id, name, newTier);
    }
}
