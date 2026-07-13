package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;
// ◀ Slides: Deck 02 Fundamentals — "Coupling in OOP: Subtyping & Message"

import java.util.ArrayList;
import java.util.List;

/**
 * A simple base ledger. The {@code entries} list is an internal implementation
 * detail — how the ledger happens to store its data today.
 */
public class Ledger {

    protected final List<Integer> entries = new ArrayList<>();

    public void add(int amount) {
        entries.add(amount);
    }

    public int total() {
        return entries.stream().mapToInt(Integer::intValue).sum();
    }
}
