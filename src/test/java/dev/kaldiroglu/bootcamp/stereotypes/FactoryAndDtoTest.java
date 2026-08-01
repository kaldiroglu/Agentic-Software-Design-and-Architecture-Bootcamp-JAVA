package dev.kaldiroglu.bootcamp.stereotypes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("A factory that cannot make an invalid member, and the DTO it hands out")
class FactoryAndDtoTest {

    @Test
    @DisplayName("The factory builds a member from plain input")
    void theFactoryBuildsAMember() {
        Member ayse = Member.joining("ayse", "Ayşe", Tier.STANDARD);

        assertEquals(MemberId.of("ayse"), ayse.id());
    }

    @Test
    @DisplayName("It refuses to make an invalid one — the check lives in the value object")
    void theFactoryRefusesABlankId() {
        assertThrows(IllegalArgumentException.class,
                () -> Member.joining("   ", "Ayşe", Tier.STANDARD));
    }

    @Test
    @DisplayName("A DTO is equal by value — two cards of one member are interchangeable")
    void twoCardsOfTheSameMemberAreInterchangeable() {
        Member ayse = Member.joining("ayse", "Ayşe", Tier.STANDARD);

        assertEquals(ayse.toCard(), ayse.toCard());
    }

    @Test
    @DisplayName("The member is the same; the cards are not — and both are correct")
    void theEntityIsEqualButItsCardsDiffer() {
        Member standard = Member.joining("ayse", "Ayşe", Tier.STANDARD);
        Member premium = standard.movedTo(Tier.PREMIUM);

        assertEquals(standard, premium);
        assertNotEquals(standard.toCard(), premium.toCard());
    }

    @Test
    @DisplayName("Crossing the boundary flattens the value objects to plain text")
    void theCardFlattensTheValueObjects() {
        MemberCard card = Member.joining("ayse", "Ayşe", Tier.PREMIUM).toCard();

        assertEquals("ayse", card.id());
        assertEquals("Premium", card.tier());
    }
}
