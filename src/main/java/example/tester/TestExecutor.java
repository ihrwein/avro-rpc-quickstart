package example.tester;

import example.cli.CLIOptions;
import example.cli.ICLIOptions;
import example.message.MessageFactory;
import example.server.IServer;
import example.server.TransportAdapterFactory;
import example.util.TesterMode;
import example.util.Utils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {

    public static int startTest(ICLIOptions options) throws IOException, InterruptedException {
        TesterMode testerMode = options.getTesterMode();

        if (testerMode == TesterMode.CLIENT) {
            startConcurrentClients(options);
        }
        else if (testerMode == TesterMode.SERVER) {
            startServer(options);
        }

        return 0;
    }

    public static void startServer(ICLIOptions options) throws InterruptedException {
        IServer server = null;

        try {
            server = TransportAdapterFactory.createServer(options);
            Utils.block();
        }
        catch (IOException e) {
            if (server != null) {
                server.close();
            }
        }

    }

    public static void startConcurrentClients(ICLIOptions options) throws IOException, InterruptedException {

        Object message = MessageFactory.createMessage(options);
        int threads = options.getThreads();

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        long startTime = System.nanoTime();
        System.out.println("Starting clients");

        CountDownLatch latch = new CountDownLatch(threads);
        createAndStartClients(options, message, threads, executor, latch);

        System.out.println("All clients successfully started");

        latch.await();
        executor.shutdown();

        System.out.println("All clients successfully ended");

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("msgsize, impl, msgnum, elapsegtime, threads");
        String result = String.format("%s,%s,%s,%s,%s",
                                      options.getMessageSize(),
                                      options.getImplementation(),
                                      options.getMessageNumber(),
                                      elapsedTime,
                                      threads);
        System.err.println(result);

    }

    private static void createAndStartClients(ICLIOptions options, Object message, int threads, ExecutorService executor, CountDownLatch latch) {
        int messageNumber = options.getMessageNumber();
        int numberPerClient = messageNumber / threads;
        int remainder = messageNumber % threads;

        assert((numberPerClient + remainder) == messageNumber);

        for (int i = 0; i < options.getThreads(); i++) {
            if (i == 0)
                createAndStartClient(options, message, executor, latch, numberPerClient + remainder, i);
            createAndStartClient(options, message, executor, latch, numberPerClient, i);
        }
    }

    private static void createAndStartClient(ICLIOptions options, Object message, ExecutorService executor, CountDownLatch latch, int numberPerClient, int i) {
        executor.execute(new ConcurrentClient(options, message, i, latch, numberPerClient));
    }
}
