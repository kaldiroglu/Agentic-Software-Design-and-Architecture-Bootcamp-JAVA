package dev.kaldiroglu.bootcamp.solid.srp;
// ◀ Slides: Deck 04 SOLID — "Split the Reasons to Change"

/** FIXED — persistence lives here; its only reason to change is how we store data. */
public final class InvoiceRepository {

    public String save(Invoice invoice) {
        return "INSERT INTO invoices VALUES ('%s', %s)".formatted(invoice.id(), invoice.amount());
    }
}
