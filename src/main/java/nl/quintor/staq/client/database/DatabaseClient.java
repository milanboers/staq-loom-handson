package nl.quintor.staq.client.database;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Niet aankomen! Dit is geavanceerde code om een database aan te roepen.
 */
public class DatabaseClient {
    public String getIsbn(final String bookId) {
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        final var num = Math.abs(UUID.nameUUIDFromBytes(bookId.getBytes(StandardCharsets.UTF_8))
                        .getLeastSignificantBits()) % 1_000_000_000L;
        return String.format("978%010d", num);
    }
}
