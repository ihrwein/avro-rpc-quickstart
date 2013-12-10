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
    private AtomicInteger messageLatch;

    public ConcurrentClient(CommandLineOptions options, Object message, int id, CountDownLatch latch, AtomicInteger messageLatch) {
        this.options = options;
        this.message = message;
        this.id = id;
        this.latch = latch;
        this.messageLatch = messageLatch;
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

        int messageNumber = options.getMessageNumber();
        int messagesPerIteration = messageNumber / 100;
        int percent  = 0;
        int sentMessages = 0;

        while(messageLatch.getAndDecrement() > 0) {

            sentMessages++;
            client.send(message);

//            if (sentMessages % messagesPerIteration == 0)
//            {
//                System.out.println("Client #" + id + " is at "  + "%");
//                percent++;
//            }

        }
    }

}
