package nl.quintor.staq.service;

import nl.quintor.staq.client.bookservice.ReactiveMetabookClient;
import nl.quintor.staq.client.database.ReactiveDatabaseClient;
import reactor.core.publisher.Mono;

public class ReactiveBookService {
    private final ReactiveDatabaseClient databaseClient = new ReactiveDatabaseClient();
    private final ReactiveMetabookClient metabookClient = new ReactiveMetabookClient();

    public Mono<String> getLanguage(final String bookId) {
        // 1. Implementeer deze methode. Gebruik de reactive databaseClient en metabookClient.
        // Gebruik geen blocking calls (geen .block(), geen .join()!). Hint: kijk naar flatMap.
        // 2. Wat gebeurt er als je wel blocking calls gebruikt? Probeer het eens.
        throw new UnsupportedOperationException();
    }
}
