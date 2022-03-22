package nl.quintor.staq.handler;

import nl.quintor.staq.http.ResponseWriter;
import nl.quintor.staq.service.BookService;

public class ThreadPoolRequestHandler implements RequestHandler {
    private final BookService bookService = new BookService();
    // 1. Maak hier een threadpool aan

    @Override
    public void handle(final ResponseWriter responseWriter, final String itemId) {
        // 2. Implementeer deze methode. Haal de taal op uit de bookService en schrijf de response weg naar
        // responseWriter. Doe dit binnen de threadpool die je bij 1. hebt gemaakt.
        throw new UnsupportedOperationException();
    }
}
