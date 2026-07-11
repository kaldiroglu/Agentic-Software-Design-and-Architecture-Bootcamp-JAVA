package dev.kaldiroglu.bootcamp.project;
// ◀ Slides: Deck 09 SW Design Project with AI — "A Library Loan Service"

/** A title in the catalogue and how many physical copies the library owns. */
public record Book(String isbn, String title, int totalCopies) {

    public Book {
        if (totalCopies < 0) {
            throw new IllegalArgumentException("totalCopies cannot be negative");
        }
    }
}
