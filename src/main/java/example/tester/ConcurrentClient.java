package example.tester;

import example.util.CommandLineOptions;
import example.server.TransportAdapterFactory;
import example.client.AbstractClient;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentClient implements Runnable {

    private CommandLineOptions options;
    private Object message;
    private int id;
    private CountDownLatch latch;
    private int numberOfMessagesToBeSent;

    public ConcurrentClient(CommandLineOptions options, Object message, int id, CountDownLatch latch, int numberOfMessagesToBeSent) {
        this.options = options;
        this.message = message;
        this.id = id;
        this.latch = latch;
        this.numberOfMessagesToBeSent = numberOfMessagesToBeSent;
    }

    @Override
    public void run() {
        try {
            AbstractClient client;
            client =  TransportAdapterFactory.createClient(options.getImplementation(), options.getAddress(), options.getPort());

            System.out.println("Client #" + id + " started to send messages");
            startSendingMessages(client);
            System.out.println("Client #" + id + " ended sending messages");
            client.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            latch.countDown();
        }
    }

    private void startSendingMessages(AbstractClient client) {
        while(numberOfMessagesToBeSent > 0) {
            client.send(message);
            numberOfMessagesToBeSent--;
        }
    }

}
