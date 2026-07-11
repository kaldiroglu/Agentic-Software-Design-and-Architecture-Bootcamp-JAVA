package dev.kaldiroglu.bootcamp.solid.srp;
// ◀ Slides: Deck 04 SOLID — "Split the Reasons to Change"

/** FIXED — presentation lives here; its only reason to change is the output format. */
public final class InvoiceRenderer {

    public String toHtml(Invoice invoice) {
        return "<p>Invoice %s — Total: %s</p>".formatted(invoice.id(), invoice.total());
    }
}
