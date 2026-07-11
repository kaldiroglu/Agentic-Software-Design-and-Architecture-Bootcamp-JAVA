package dev.kaldiroglu.bootcamp.intro;

import dev.kaldiroglu.bootcamp.intro.model.Customer;
import dev.kaldiroglu.bootcamp.intro.model.Item;
import dev.kaldiroglu.bootcamp.intro.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The smell and the fixed design must compute the SAME number — the point of the
 * refactor is behaviour-preserving structure, not a change in results.
 */
class OrderTotalTest {

    private static final double CENTS = 0.001;

    private Order vipOrder() {
        // subtotal = 100; VIP → 90; +20% tax → 108
        return new Order(List.of(new Item("book", 50.0, 2)), new Customer("Ada", true));
    }

    private Order plainOrder() {
        // subtotal = 100; no discount; +20% tax → 120
        return new Order(List.of(new Item("book", 50.0, 2)), new Customer("Kâmil", false));
    }

    @Test
    @DisplayName("smell and fixed agree for a VIP customer")
    void vipMatches() {
        double smell = new OrderTotalSmell().total(vipOrder());
        double fixed = new OrderTotal(Discount.vip()).total(vipOrder());
        assertEquals(108.0, fixed, CENTS);
        assertEquals(smell, fixed, CENTS);
    }

    @Test
    @DisplayName("swapping the discount rule needs no change to OrderTotal")
    void discountIsSwappable() {
        double withVip = new OrderTotal(Discount.vip()).total(plainOrder());
        double withNone = new OrderTotal(Discount.none()).total(plainOrder());
        // A non-VIP customer pays the same under either rule: 120.
        assertEquals(120.0, withVip, CENTS);
        assertEquals(120.0, withNone, CENTS);
    }
}
