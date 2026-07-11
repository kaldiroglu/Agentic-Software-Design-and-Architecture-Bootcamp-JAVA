package dev.kaldiroglu.bootcamp.solid.isp;
// ◀ Slides: Deck 04 SOLID — "Split the Fat Interface"

/**
 * FIXED — small, focused interfaces. Implementers support only what they can, and
 * clients depend only on the capability they actually use.
 */
public final class Devices {

    private Devices() {
    }

    public interface Printer {
        String print(String doc);
    }

    public interface Scanner {
        String scan();
    }

    public interface Fax {
        String fax(String doc);
    }

    /** A simple printer implements only Printer — no fake scan/fax. */
    public static final class BasicPrinter implements Printer {
        @Override
        public String print(String doc) {
            return "printed: " + doc;
        }
    }

    /** A multifunction device composes the capabilities it truly has. */
    public static final class AllInOne implements Printer, Scanner, Fax {
        @Override
        public String print(String doc) {
            return "printed: " + doc;
        }

        @Override
        public String scan() {
            return "scanned";
        }

        @Override
        public String fax(String doc) {
            return "faxed: " + doc;
        }
    }
}
