package dev.kaldiroglu.bootcamp.solid.isp;
// ◀ Slides: Deck 04 SOLID — "Split the Fat Interface"

/**
 * SMELL — a "fat" interface. A simple printer is forced to implement scan() and
 * fax() it does not support, usually by throwing — a landmine for callers.
 */
public interface MachineSmell {

    String print(String doc);

    String scan();

    String fax(String doc);
}
