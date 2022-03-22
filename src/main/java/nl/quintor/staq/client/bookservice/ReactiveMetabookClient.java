package nl.quintor.staq.client.bookservice;

import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

/**
 * Niet aankomen! Dit is geavanceerde code om een service aan te roepen.
 */
public class ReactiveMetabookClient {
    private static final List<String> LANGUAGES = List.of("Dutch", "English", "Spanish", "Chinese");

    public Mono<String> getLanguage(final String isbn) {
        final var languageIndex = (int) (Math.abs(UUID.nameUUIDFromBytes(isbn.getBytes(StandardCharsets.UTF_8))
                .getLeastSignificantBits()) % 4);
        return Mono.just(LANGUAGES.get(languageIndex)).delayElement(Duration.ofSeconds(1));
    }
}
