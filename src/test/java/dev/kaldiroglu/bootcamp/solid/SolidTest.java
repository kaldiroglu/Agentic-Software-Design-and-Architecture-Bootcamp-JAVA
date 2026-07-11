package dev.kaldiroglu.bootcamp.solid;

import dev.kaldiroglu.bootcamp.solid.dip.InMemoryRepository;
import dev.kaldiroglu.bootcamp.solid.dip.OrderService;
import dev.kaldiroglu.bootcamp.solid.isp.Devices;
import dev.kaldiroglu.bootcamp.solid.ocp.Pricing;
import dev.kaldiroglu.bootcamp.solid.ocp.PriceCalculatorSmell;
import dev.kaldiroglu.bootcamp.solid.ocp.PricingRules;
import dev.kaldiroglu.bootcamp.solid.srp.Invoice;
import dev.kaldiroglu.bootcamp.solid.srp.InvoiceRenderer;
import dev.kaldiroglu.bootcamp.solid.srp.InvoiceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolidTest {

    @Nested
    class Srp {
        @Test
        void eachClassOwnsOneConcern() {
            var invoice = new Invoice("INV-1", 100.0);
            assertEquals(100.0, invoice.total(), 0.001);
            assertTrue(new InvoiceRepository().save(invoice).contains("INV-1"));
            assertTrue(new InvoiceRenderer().toHtml(invoice).contains("100.0"));
        }
    }

    @Nested
    class Ocp {
        @Test
        void newRuleIsANewClassNotAnEdit() {
            Pricing book = PricingRules.BOOK;
            Pricing food = PricingRules.FOOD;
            assertEquals(new PriceCalculatorSmell().price("book", 50), book.price(50), 0.001);
            assertEquals(new PriceCalculatorSmell().price("food", 50), food.price(50), 0.001);
            // A brand-new rule, added without touching any existing pricing code:
            Pricing electronics = base -> base * 1.18;
            assertEquals(59.0, electronics.price(50), 0.001);
        }
    }

    @Nested
    class Lsp {
        @Test
        @DisplayName("the same test passes for Rectangle but FAILS for Square")
        void squareBreaksTheRectangleContract() {
            var rect = new dev.kaldiroglu.bootcamp.solid.lsp.violation.Rectangle();
            rect.setWidth(5);
            rect.setHeight(4);
            assertEquals(20, rect.area(), "a rectangle honours width * height");

            var square = new dev.kaldiroglu.bootcamp.solid.lsp.violation.Square();
            square.setWidth(5);
            square.setHeight(4);   // silently also resets width — contract broken
            assertNotEquals(20, square.area(), "Square violates the Rectangle contract");
            assertEquals(16, square.area());
        }

        @Test
        void fixedShapesComputeTheirOwnAreaHonestly() {
            var rect = new dev.kaldiroglu.bootcamp.solid.lsp.fixed.Rectangle(5, 4);
            var square = new dev.kaldiroglu.bootcamp.solid.lsp.fixed.Square(5);
            assertEquals(20.0, rect.area(), 0.001);
            assertEquals(25.0, square.area(), 0.001);
        }
    }

    @Nested
    class Isp {
        @Test
        void clientsDependOnlyOnWhatTheyUse() {
            Devices.Printer printer = new Devices.BasicPrinter();
            assertEquals("printed: cv.pdf", printer.print("cv.pdf"));

            var aio = new Devices.AllInOne();
            assertEquals("scanned", aio.scan());
            assertEquals("faxed: form", aio.fax("form"));
        }
    }

    @Nested
    class Dip {
        @Test
        void serviceWorksWithAnyInjectedRepository() {
            var service = new OrderService(new InMemoryRepository());
            service.place("order-1");
            service.place("order-2");
            assertEquals(2, service.placedCount());
        }
    }
}
