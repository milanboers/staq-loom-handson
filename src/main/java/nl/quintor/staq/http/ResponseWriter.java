package nl.quintor.staq.http;

import lombok.Getter;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

public class ResponseWriter {
    private MonoSink<String> sink;
    @Getter
    private final Mono<String> response;

    public ResponseWriter() {
        response = Mono.create(sink -> this.sink = sink);
    }

    public void writeResponse(final String text) {
        sink.success(text);
    }
}
