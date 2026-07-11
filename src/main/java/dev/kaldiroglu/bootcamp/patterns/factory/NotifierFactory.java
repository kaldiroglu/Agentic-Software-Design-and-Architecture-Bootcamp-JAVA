package dev.kaldiroglu.bootcamp.patterns.factory;
// ◀ Slides: Deck 05 Design Patterns — "Centralise Creation"

/**
 * The factory is the ONE place that knows the concrete notifiers. Callers ask for
 * a channel and get a {@link Notifier}; they never write {@code new EmailNotifier()}.
 */
public final class NotifierFactory {

    public enum Channel { EMAIL, SMS }

    public Notifier create(Channel channel) {
        return switch (channel) {
            case EMAIL -> message -> "email: " + message;
            case SMS -> message -> "sms: " + message;
        };
    }
}
