package dev.kaldiroglu.bootcamp.solid.srp;
// ◀ Slides: Deck 04 SOLID — "Split the Reasons to Change"

/**
 * SMELL — one class with three reasons to change: finance (total), persistence
 * (save) and presentation (html). A change to any concern risks the others.
 */
public final class InvoiceSmell {

    private final double amount;

    public InvoiceSmell(double amount) {
        this.amount = amount;
    }

    public double total() {                 // finance
        return amount;
    }

    public String saveToDb() {              // persistence
        return "INSERT INTO invoices VALUES (" + amount + ")";
    }

    public String toHtml() {                // presentation
        return "<p>Total: " + amount + "</p>";
    }
}
