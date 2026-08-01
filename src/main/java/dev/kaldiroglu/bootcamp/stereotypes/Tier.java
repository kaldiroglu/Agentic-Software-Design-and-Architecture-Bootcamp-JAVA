package dev.kaldiroglu.bootcamp.stereotypes;
// ◀ Slides: Deck 07 Object Stereotypes — "The Identity Question"

/** What a membership entitles you to. */
public enum Tier {

    STANDARD("Standard"),
    PREMIUM("Premium");

    private final String label;

    Tier(String label) {
        this.label = label;
    }

    /** What the outside world calls this tier. */
    public String label() {
        return label;
    }
}
