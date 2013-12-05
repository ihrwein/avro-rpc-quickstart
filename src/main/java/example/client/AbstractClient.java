package example.client;

public abstract class AbstractClient {

    public static final int DEFAULT_PORT = 65000;
    private int port;

    public AbstractClient() {
        this(DEFAULT_PORT);
    }

    public AbstractClient(int port) {
        this.port = port;
    }

    public abstract void send(Object t);

    public void close() {

    }

}
