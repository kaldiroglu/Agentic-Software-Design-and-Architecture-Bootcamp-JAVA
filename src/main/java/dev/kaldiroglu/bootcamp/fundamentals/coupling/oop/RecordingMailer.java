package dev.kaldiroglu.bootcamp.fundamentals.coupling.oop;
// ◀ Slides: Deck 02 Fundamentals — "Coupling in OOP: Subtyping & Message"

import java.util.ArrayList;
import java.util.List;

/**
 * FIXED — a {@link Mailer} test double. Because {@code Orders} depends on the
 * interface rather than a concrete mailer, we drop this in and assert exactly what
 * was sent. That seam exists only because of message coupling.
 */
public final class RecordingMailer implements Mailer {

    private final List<String> sent = new ArrayList<>();

    @Override
    public void send(String receipt) {
        sent.add(receipt);
    }

    public List<String> sent() {
        return List.copyOf(sent);
    }
}
