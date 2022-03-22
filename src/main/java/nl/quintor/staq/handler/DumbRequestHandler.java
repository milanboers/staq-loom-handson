package nl.quintor.staq.handler;

import nl.quintor.staq.http.ResponseWriter;
import nl.quintor.staq.service.BookService;

public class DumbRequestHandler implements RequestHandler {
    private final BookService bookService = new BookService();

    @Override
    public void handle(final ResponseWriter responseWriter, final String itemId) {
        // Als we deze handler gebruiken is onze app erg traag.. Waarom?
        final var language = bookService.getLanguage(itemId); // Deze call blockt en duurt lang
        responseWriter.writeResponse(language);
    }
}
