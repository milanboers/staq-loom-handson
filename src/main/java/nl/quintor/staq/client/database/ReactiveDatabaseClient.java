package nl.quintor.staq.client.database;

import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;

/**
 * Niet aankomen! Dit is geavanceerde code om een database aan te roepen.
 */
public class ReactiveDatabaseClient {
    public Mono<String> getIsbn(final String bookId) {
        final var num = Math.abs(UUID.nameUUIDFromBytes(bookId.getBytes(StandardCharsets.UTF_8))
                .getLeastSignificantBits()) % 1_000_000_000L;
        return Mono.just(String.format("978%010d", num)).delayElement(Duration.ofSeconds(1));
    }
}
