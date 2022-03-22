package nl.quintor.staq.handler;

import nl.quintor.staq.http.ResponseWriter;
import nl.quintor.staq.service.BookService;

import java.util.concurrent.*;

public class ThreadPoolRequestHandler implements RequestHandler {
    private final BookService bookService = new BookService();
    // 1. Maak hier een threadpool aan
    private final SynchronousQueue<Runnable> taskQueue = new SynchronousQueue<>();
    private final ExecutorService executorService = new ThreadPoolExecutor(
            10, 150, 60L, TimeUnit.SECONDS, taskQueue);

    @Override
    public void handle(final ResponseWriter responseWriter, final String itemId) {
        // 2. Implementeer deze methode. Haal de taal op uit de bookService en schrijf de response weg naar
        // responseWriter. Doe dit binnen de threadpool die je bij 1. hebt gemaakt.
        executorService.submit(() -> {
            final String language = bookService.getLanguage(itemId);
            responseWriter.writeResponse(language);
        });
    }
}
