package dev.kaldiroglu.bootcamp.intro;
// ◀ Slides: Deck 01 Introduction — "Same Feature, Two Designs"

import dev.kaldiroglu.bootcamp.intro.model.Item;
import dev.kaldiroglu.bootcamp.intro.model.Order;

/**
 * SMELL — one method knows the subtotal, the discount rule AND the tax rule.
 * Every one of those is a separate reason to change, tangled into a single place.
 * Changing the discount policy means editing (and re-testing) tax and totalling too.
 */
public final class OrderTotalSmell {

    public double total(Order order) {
        double total = 0;
        for (Item item : order.items()) {
            total += item.price() * item.quantity();
        }
        if (order.customer().vip()) {   // discount rule
            total *= 0.9;
        }
        total += total * 0.20;          // tax rule
        return total;
    }
}
