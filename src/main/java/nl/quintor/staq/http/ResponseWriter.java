package nl.quintor.staq.http;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.MonoSink;

@RequiredArgsConstructor
public class ResponseWriter {
    private final MonoSink<String> sink;

    public void writeResponse(final String text) {
        sink.success(text);
    }
}
