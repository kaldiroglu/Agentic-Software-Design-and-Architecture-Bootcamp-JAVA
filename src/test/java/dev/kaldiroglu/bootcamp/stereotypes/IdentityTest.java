package dev.kaldiroglu.bootcamp.stereotypes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Entity or value object — the identity question")
class IdentityTest {

    private static final MemberId AYSE = MemberId.of("ayse");

    @Test
    @DisplayName("A value object is equal by its value — there is no other id 'ayse'")
    void valueObjectsAreEqualByValue() {
        assertEquals(MemberId.of("ayse"), MemberId.of("ayse"));
    }

    @Test
    @DisplayName("The smell: upgrading a record member produces a different member")
    void theRecordStopsBeingTheSameMember() {
        MemberAsValue before = new MemberAsValue(AYSE, "Ayşe", Tier.STANDARD);

        MemberAsValue after = before.movedTo(Tier.PREMIUM);

        assertNotEquals(before, after);
    }

    @Test
    @DisplayName("The fix: upgrading an entity leaves the same member")
    void theEntityStaysTheSameMember() {
        Member before = new Member(AYSE, "Ayşe", Tier.STANDARD);

        Member after = before.movedTo(Tier.PREMIUM);

        assertEquals(before, after);
    }

    @Test
    @DisplayName("The cost, made concrete: a set loses the record member on upgrade")
    void aSetLosesTheRecordMember() {
        Set<MemberAsValue> members = new HashSet<>();
        MemberAsValue before = new MemberAsValue(AYSE, "Ayşe", Tier.STANDARD);
        members.add(before);

        assertFalse(members.contains(before.movedTo(Tier.PREMIUM)));
    }

    @Test
    @DisplayName("The entity is still found after the same change")
    void aSetKeepsTheEntity() {
        Set<Member> members = new HashSet<>();
        Member before = new Member(AYSE, "Ayşe", Tier.STANDARD);
        members.add(before);

        assertTrue(members.contains(before.movedTo(Tier.PREMIUM)));
    }

    @Test
    @DisplayName("Identity is the id alone — a renamed member is still that member")
    void nameIsNotIdentity() {
        assertEquals(new Member(AYSE, "Ayşe", Tier.STANDARD),
                     new Member(AYSE, "Ayşe Kaldıroğlu", Tier.PREMIUM));
    }
}
