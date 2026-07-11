package dev.kaldiroglu.bootcamp.fundamentals.demeter;
// ◀ Slides: Deck 02 Fundamentals — "The 'Train Wreck' / Tell, Don't Ask"

/**
 * SMELL — the "train wreck": this class reaches through Order to Customer to
 * Address. It now depends on the shape of all three; a change to any one of them
 * can break this line.
 */
public final class ShippingLabelSmell {

    public String cityFor(Order order) {
        return order.customer().address().city();   // one dot too many, three times over
    }
}
