package nl.quintor.staq.service;

import nl.quintor.staq.client.database.DatabaseClient;
import nl.quintor.staq.client.bookservice.MetabookClient;

public class BookService {
    private final DatabaseClient databaseClient = new DatabaseClient();
    private final MetabookClient metabookClient = new MetabookClient();

    public String getLanguage(final String bookId) {
        // 1. Implementeer deze methode.
        // 2. Zet hier eens een breakpoint neer. Op welke thread draait deze code?
        throw new UnsupportedOperationException();
    }
}
