package dev.kaldiroglu.bootcamp.stereotypes;
// ◀ Slides: Deck 07 Object Stereotypes — "The Identity Question"  (a DTO, done right)

/**
 * A DTO: what a member looks like from outside the domain.
 *
 * Here a record is exactly right, and for the opposite reason to MemberAsValue. A card
 * has no identity to lose — it is a message, not a thing, and two identical cards are
 * interchangeable. The value objects have flattened to strings on the way out, because
 * outside the domain there is no MemberId.
 *
 * It travels one way only. A card never comes back in to change a member.
 *
 * (Data Transfer Object is Fowler's, from Patterns of Enterprise Application
 * Architecture, 2002 — not part of Evans' set.)
 */
public record MemberCard(String id, String name, String tier) {
}
