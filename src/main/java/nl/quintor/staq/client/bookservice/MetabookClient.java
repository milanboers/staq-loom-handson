package nl.quintor.staq.client.bookservice;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * Niet aankomen! Dit is geavanceerde code om een service aan te roepen.
 */
public class MetabookClient {
    private static final List<String> LANGUAGES = List.of("Dutch", "English", "Spanish", "Chinese");

    public String getLanguage(final String isbn) {
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        final var languageIndex = (int) (Math.abs(UUID.nameUUIDFromBytes(isbn.getBytes(StandardCharsets.UTF_8))
                .getLeastSignificantBits()) % 4);
        return LANGUAGES.get(languageIndex);
    }
}
