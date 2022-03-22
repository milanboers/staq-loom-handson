package nl.quintor.staq;

import io.netty.handler.codec.http.HttpResponseStatus;
import nl.quintor.staq.handler.DumbRequestHandler;
import nl.quintor.staq.handler.RequestHandler;
import nl.quintor.staq.http.ResponseWriter;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class App
{
    private static final Logger LOGGER = Logger.getGlobal();

    public static void main(final String[] args) {
        var myRequestHandler = new DumbRequestHandler();
        var app = new App();
        var port = app.start(myRequestHandler);
        LOGGER.info("Listening on port " + port);
    }

    private DisposableServer server;

    public int start(final RequestHandler requestHandler) {
        var port = getPort();

        this.server = HttpServer.create()
                .host("0.0.0.0")
                .port(port)
                .protocol(HttpProtocol.H2C)
                .handle((req, res) -> {
                    try {
                        var writer = new ResponseWriter();
                        final var splitUri = req.uri().split("/books/");
                        if (splitUri.length < 2) {
                            return res.status(HttpResponseStatus.NOT_FOUND).send();
                        }
                        requestHandler.handle(writer, splitUri[1]);
                        return writer.getResponse()
                                .flatMapMany(s -> res.sendString(Mono.just(s)));
                    } catch (Throwable t) {
                        return res.status(500).send();
                    }
                })
                .bindNow();

        return port;
    }

    private int getPort() {
        try (var s = new ServerSocket(0)){
            return s.getLocalPort();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void stop() {
        server.dispose();
    }
}
