package dev.kaldiroglu.bootcamp.patterns;

import dev.kaldiroglu.bootcamp.patterns.factory.Notifier;
import dev.kaldiroglu.bootcamp.patterns.factory.NotifierFactory;
import dev.kaldiroglu.bootcamp.patterns.proxy.Image;
import dev.kaldiroglu.bootcamp.patterns.proxy.LazyImage;
import dev.kaldiroglu.bootcamp.patterns.proxy.RealImage;
import dev.kaldiroglu.bootcamp.patterns.strategy.Fee;
import dev.kaldiroglu.bootcamp.patterns.strategy.FeeCalculatorSmell;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternsTest {

    @Nested
    class Factory {
        @Test
        void factoryHidesConcreteTypes() {
            var factory = new NotifierFactory();
            Notifier email = factory.create(NotifierFactory.Channel.EMAIL);
            Notifier sms = factory.create(NotifierFactory.Channel.SMS);
            assertEquals("email: hi", email.send("hi"));
            assertEquals("sms: hi", sms.send("hi"));
        }
    }

    @Nested
    class Strategy {
        @Test
        void strategyMatchesTheTangledVersion_thenExtendsFreely() {
            var smell = new FeeCalculatorSmell();
            assertEquals(smell.fee("card", 100), Fee.CARD.of(100), 0.001);
            assertEquals(smell.fee("wire", 100), Fee.WIRE.of(100), 0.001);
            // A new strategy added with zero edits to existing code:
            Fee crypto = amount -> amount * 0.005;
            assertEquals(0.5, crypto.of(100), 0.001);
        }
    }

    @Nested
    class Proxy {
        @Test
        void virtualProxyDefersTheLoadUntilFirstRender() {
            var loads = new AtomicInteger();

            new RealImage("photo.png", loads);         // eager: loads immediately
            assertEquals(1, loads.get());

            Image lazy = new LazyImage("big.png", loads);
            assertEquals(1, loads.get(), "constructing the proxy loads nothing");

            assertEquals("rendered: big.png", lazy.render());
            assertEquals(2, loads.get(), "first render triggers the load");

            lazy.render();
            assertEquals(2, loads.get(), "second render reuses the loaded image");
        }
    }
}
