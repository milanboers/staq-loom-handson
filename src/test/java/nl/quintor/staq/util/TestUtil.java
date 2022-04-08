package nl.quintor.staq.util;

import nl.quintor.staq.App;
import nl.quintor.staq.handler.RequestHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestUtil {
    private static final Logger LOGGER = Logger.getGlobal();

    private TestUtil() {}

    record Result(String body, long tookMs) {}

    public static void runTest(final RequestHandler requestHandler, final int tps) {
        runTest(requestHandler, tps, tps*10);
    }

    public static void runTest(final RequestHandler requestHandler, final int tps, final int total) {
        var app = new App();
        final var port = app.start(requestHandler);
        var httpClient = HttpClient.create()
                .baseUrl("http://127.0.0.1:" + port)
                .protocol(HttpProtocol.H2C);

        LOGGER.info("Starting test with handler " + requestHandler.getClass().getName());
        final var startTime = System.currentTimeMillis();
        final var results = requestStream(httpClient, tps, total).collectList().block();

        assertThat(results, notNullValue());
        assertThat(results.size(), is(total));
        for (var result : results) {
            var responseBody = result.body();
            assertThat(responseBody, oneOf("Dutch", "English", "Spanish", "Chinese"));
        }

        final var timeTook = System.currentTimeMillis() - startTime;
        LOGGER.info("Took " + timeTook + "ms");
        final var averageLatency = results.stream().mapToLong(Result::tookMs).average().orElse(-1.0d);
        LOGGER.info("Average latency: " + averageLatency + "ms");
        LOGGER.info("Memory in use: " + getMemoryUsage() + "MB");

        app.stop();
    }

    private static Flux<Result> requestStream(
            final HttpClient httpClient, final int tps, final int total) {
        return Flux.interval(Duration.ofNanos(1_000_000_000L / tps))
                .take(total)
                .flatMap(bookId -> fireRequest(httpClient, bookId), Integer.MAX_VALUE);
    }

    private static Mono<Result> fireRequest(final HttpClient httpClient, final long bookId) {
        final long startTime = System.currentTimeMillis();
        return httpClient.get()
                .uri("/books/" + bookId)
                .responseContent()
                .aggregate().asString()
                .map(body -> new Result(body, System.currentTimeMillis() - startTime));
    }

    private static long getMemoryUsage() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1_000_000L;
    }
}
