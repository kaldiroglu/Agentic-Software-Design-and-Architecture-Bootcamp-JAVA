package dev.kaldiroglu.bootcamp.stereotypes;
// ◀ Slides: Deck 07 Object Stereotypes — "The Identity Question"  (the fix)

import java.util.Objects;

/**
 * The fix: an entity, equal by identity.
 *
 * Everything about a member may change except which member it is. Writing equals and
 * hashCode by hand is the price of saying that — and it is the whole difference between
 * a value and an entity.
 */
public final class Member {

    private final MemberId id;
    private final String name;
    private final Tier tier;

    public Member(MemberId id, String name, Tier tier) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.tier = Objects.requireNonNull(tier, "tier must not be null");
    }

    /**
     * A factory: the only convenient way to make a member, and it cannot make an invalid
     * one — MemberId does the checking, once, here.
     */
    public static Member joining(String id, String name, Tier tier) {
        return new Member(MemberId.of(id), name, tier);
    }

    /**
     * The entity decides what leaves it. The caller never assembles a card out of
     * getters — that would be the anemic model again, one boundary further out.
     */
    public MemberCard toCard() {
        return new MemberCard(id.value(), name, tier.label());
    }

    public Member movedTo(Tier newTier) {
        return new Member(id, name, newTier);
    }

    public MemberId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Tier tier() {
        return tier;
    }

    /** Identity, and nothing else. A renamed, upgraded member is still that member. */
    @Override
    public boolean equals(Object other) {
        return other instanceof Member that && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Member[" + id.value() + "]";
    }
}
