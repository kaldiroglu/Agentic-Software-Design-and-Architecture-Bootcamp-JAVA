package dev.kaldiroglu.bootcamp.fundamentals.cohesion;
// ◀ Slides: Deck 02 Fundamentals — "A Password Value Object"

import java.security.SecureRandom;

/**
 * FIXED — a highly cohesive value object.
 *
 * <p>Everything a password knows how to do — validation, random creation,
 * comparison, and masking — lives in this one class, and nothing unrelated
 * (storage, hashing, UI) leaks in. One concept, one home: the definition of
 * functional cohesion.
 */
public final class Password {

    private static final int MIN_LENGTH = 12;
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+";
    private static final SecureRandom RANDOM = new SecureRandom();

    private final String value;

    private Password(String value) {
        this.value = value;
    }

    // ---- create & validate: one place owns "what a valid password is" ----

    public static Password of(String raw) {
        if (!isValid(raw)) {
            throw new IllegalArgumentException("Password does not meet the policy");
        }
        return new Password(raw);
    }

    public static boolean isValid(String raw) {
        return raw != null
                && raw.length() >= MIN_LENGTH
                && raw.chars().anyMatch(Character::isUpperCase)
                && raw.chars().anyMatch(Character::isLowerCase)
                && raw.chars().anyMatch(Character::isDigit)
                && raw.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
    }

    // ---- generate a strong random password (reuses of()/isValid) ----

    public static Password random(int length) {
        if (length < MIN_LENGTH) {
            throw new IllegalArgumentException("length must be at least " + MIN_LENGTH);
        }
        // Guarantee one of each required class, then fill and shuffle the rest.
        var chars = new StringBuilder()
                .append(pick(UPPER)).append(pick(LOWER))
                .append(pick(DIGITS)).append(pick(SYMBOLS));
        String pool = UPPER + LOWER + DIGITS + SYMBOLS;
        while (chars.length() < length) {
            chars.append(pick(pool));
        }
        return of(shuffle(chars.toString()));   // of() re-checks: random is valid by construction
    }

    // ---- compare safely: constant-time, no early exit on first difference ----

    public boolean matches(String candidate) {
        if (candidate == null || candidate.length() != value.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < value.length(); i++) {
            diff |= value.charAt(i) ^ candidate.charAt(i);
        }
        return diff == 0;
    }

    // ---- format & mask: never leak the raw value ----

    public String masked() {
        return "•".repeat(value.length());
    }

    public String maskedShowingLast(int shown) {
        if (shown <= 0) {
            return masked();
        }
        int hidden = Math.max(0, value.length() - shown);
        return "•".repeat(hidden) + value.substring(hidden);
    }

    /** Safe by default — printing a Password never reveals it. */
    @Override
    public String toString() {
        return masked();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Password p && matches(p.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    private static char pick(String pool) {
        return pool.charAt(RANDOM.nextInt(pool.length()));
    }

    private static String shuffle(String source) {
        char[] chars = source.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}
