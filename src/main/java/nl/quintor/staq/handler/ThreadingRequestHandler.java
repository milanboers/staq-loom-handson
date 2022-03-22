package nl.quintor.staq.handler;

import nl.quintor.staq.http.ResponseWriter;
import nl.quintor.staq.service.BookService;

public class ThreadingRequestHandler implements RequestHandler {
    private final BookService bookService = new BookService();

    @Override
    public void handle(final ResponseWriter responseWriter, final String itemId) {
        // 1. Implementeer deze methode. Start een nieuwe thread op, haal daarbinnen de taal op uit de bookService, en
        // schrijf de response weg naar de responseWriter.
        throw new UnsupportedOperationException();
    }
}
