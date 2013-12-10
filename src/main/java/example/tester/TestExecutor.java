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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger messageLatch = new AtomicInteger(cmd.getMessageNumber());

        for (int i = 0; i < cmd.getThreads(); i++) {
            executor.execute(new ConcurrentClient(cmd, message, i, latch, messageLatch));
        }

        System.out.println("All clients successfully started");

        latch.await();
        executor.shutdown();

        System.out.println("All clients successfully ended");

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("msgsize, impl, msgnum, elapsegtime, threads");
        String result = String.format("%s,%s,%s,%s,%s", cmd.getMessageSize(), cmd.getImplementation(), cmd.getMessageNumber(), elapsedTime, threads);
        System.err.println(result);

    }



}
