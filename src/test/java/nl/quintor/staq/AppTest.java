package nl.quintor.staq;

import nl.quintor.staq.handler.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static nl.quintor.staq.util.TestUtil.runTest;

/**
 * Unit test for simple App.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class AppTest
{
    @Test
    void test1DumbRequestHandler() {
        final var handler = new DumbRequestHandler();
        runTest(handler, 1);
    }

    @Test
    void test2ThreadingRequestHandler() {
        final var handler = new ThreadingRequestHandler();
        runTest(handler, 50);
    }

    @Test
    void test3ThreadpoolRequestHandler() {
        final var handler = new ThreadPoolRequestHandler();
        runTest(handler, 50);
    }

    @Test
    void test4ReactiveRequestHandler() {
        final var handler = new ReactiveRequestHandler();
        runTest(handler, 5000);
    }

    @Test
    void test5LoomRequestHandler() {
        final var handler = new LoomRequestHandler();
        runTest(handler, 5000);
    }

}
