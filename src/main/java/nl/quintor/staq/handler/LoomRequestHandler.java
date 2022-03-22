package nl.quintor.staq.handler;

import nl.quintor.staq.http.ResponseWriter;
import nl.quintor.staq.service.BookService;

public class LoomRequestHandler implements RequestHandler {
    private final BookService bookService = new BookService();

    @Override
    public void handle(final ResponseWriter responseWriter, final String itemId) {
        // 1. Implementeer deze methode. Start een virtuele thread uit Project Loom op, haal daarbinnen de taal op
        // uit de bookService, en schrijf de response weg naar de responseWriter.
        Thread.ofVirtual().start(() -> {
            final String language = bookService.getLanguage(itemId);
            responseWriter.writeResponse(language);
        });
    }
}
