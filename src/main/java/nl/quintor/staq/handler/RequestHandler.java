package nl.quintor.staq.handler;

import nl.quintor.staq.http.ResponseWriter;

public interface RequestHandler {
    void handle(ResponseWriter responseWriter, String itemId);
}
