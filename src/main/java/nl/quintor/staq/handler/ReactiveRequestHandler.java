package nl.quintor.staq.handler;

import nl.quintor.staq.http.ResponseWriter;
import nl.quintor.staq.service.ReactiveBookService;

public class ReactiveRequestHandler implements RequestHandler {
    private final ReactiveBookService mergeService = new ReactiveBookService();

    @Override
    public void handle(ResponseWriter responseWriter, String itemId) {
        mergeService.getLanguage(itemId)
                .doOnSuccess(responseWriter::writeResponse)
                .subscribe();
    }
}
