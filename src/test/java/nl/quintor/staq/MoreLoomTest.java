package nl.quintor.staq;

import org.junit.jupiter.api.Test;

public class MoreLoomTest {
    @Test
    void startManyThreads() throws InterruptedException {
        /*
        final int n = 1_000_000;

        var threads = new ArrayList<Thread>();
        for (int i = 0; i < n; i++) {
            final var thread = Thread.ofVirtual().start(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threads.add(thread);
        }

        for (final var thread : threads) {
            thread.join();
        }

        Logger.getGlobal().info(n + " threads finished!");
        */
    }
}
