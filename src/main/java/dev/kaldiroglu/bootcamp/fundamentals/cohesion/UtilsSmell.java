package dev.kaldiroglu.bootcamp.fundamentals.cohesion;
// ◀ Slides: Deck 02 Fundamentals — "Low vs High Cohesion"

import java.util.HashMap;
import java.util.Map;

/**
 * SMELL — a low-cohesion "God utility": persistence, e-mail, finance and
 * formatting all live in one class. Four unrelated reasons to change, so any
 * edit risks the others and nothing here can be understood in isolation.
 */
public final class UtilsSmell {

    private final Map<String, String> users = new HashMap<>();

    public void saveUser(String id, String name) {   // persistence
        users.put(id, name);
    }

    public String loadUser(String id) {               // persistence
        return users.get(id);
    }

    public String sendEmail(String to, String body) { // messaging
        return "TO:" + to + "\n" + body;
    }

    public double calcTax(double amount) {            // finance
        return amount * 0.20;
    }

    public String formatIsoDate(int year, int month, int day) { // formatting
        return "%04d-%02d-%02d".formatted(year, month, day);
    }
}
