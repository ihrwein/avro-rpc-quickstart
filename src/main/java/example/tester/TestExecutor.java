package example.tester;

import example.util.CommandLineOptions;
import example.message.MessageFactory;
import example.server.IServer;
import example.server.TransportAdapterFactory;
import example.util.Utils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {

    public static void startServer(CommandLineOptions cmd) throws IOException, InterruptedException {
        IServer server;
        server = TransportAdapterFactory.createServer(cmd.getImplementation(), cmd.getPort());
        Utils.block();

        server.close();
    }

    public static void startConcurrentClients(CommandLineOptions cmd) throws IOException, InterruptedException {

        Object message = MessageFactory.createMessage(cmd.getImplementation(), cmd.getMessageSize());
        int threads = cmd.getThreads();

        ExecutorService executor = Executors.newFixedThreadPool(threads);


        long startTime = System.nanoTime();
        System.out.println("Starting clients");

        CountDownLatch latch = new CountDownLatch(threads);
        createAndStartClients(cmd, message, threads, executor, latch);

        System.out.println("All clients successfully started");

        latch.await();
        executor.shutdown();

        System.out.println("All clients successfully ended");

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("msgsize, impl, msgnum, elapsegtime, threads");
        String result = String.format("%s,%s,%s,%s,%s", cmd.getMessageSize(), cmd.getImplementation(), cmd.getMessageNumber(), elapsedTime, threads);
        System.err.println(result);

    }

    private static void createAndStartClients(CommandLineOptions cmd, Object message, int threads, ExecutorService executor, CountDownLatch latch) {
        int numberPerClient = cmd.getMessageNumber() / threads;
        int remainder = cmd.getMessageNumber() % threads;

        assert((numberPerClient + remainder) == cmd.getMessageNumber());

        for (int i = 0; i < cmd.getThreads(); i++) {
            if (i == 0)
                createAndStartClient(cmd, message, executor, latch, numberPerClient + remainder, i);
            createAndStartClient(cmd, message, executor, latch, numberPerClient, i);
        }
    }

    private static void createAndStartClient(CommandLineOptions cmd, Object message, ExecutorService executor, CountDownLatch latch, int numberPerClient, int i) {
        executor.execute(new ConcurrentClient(cmd, message, i, latch, numberPerClient));
    }
}
