package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;
// ◀ Slides: Deck 02 Fundamentals — "Coupling in OOP: Subtyping & Message"

/**
 * FIXED — the abstraction collaborators depend on.
 *
 * <p>"Program to an interface, not an implementation." Callers know only this
 * small, stable surface — never which concrete mailer does the work. High-level
 * policy owns the interface; the low-level detail implements it. That inversion
 * is the Dependency Inversion Principle, and it is what makes message coupling
 * the loosest bond of all.
 */
public interface Mailer {

    void send(String receipt);
}
