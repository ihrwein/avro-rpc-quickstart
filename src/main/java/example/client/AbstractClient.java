package example.client;

import example.util.Defaults;

public abstract class AbstractClient {

    public static final int DEFAULT_PORT = Defaults.PORT;
    public static final String DEFAULT_ADDRESS = Defaults.ADDRESS;
    private int port;
    private String address;

    public AbstractClient() {
        this(DEFAULT_PORT);
    }

    public AbstractClient(int port) {
        this(DEFAULT_ADDRESS, port);
    }

    public AbstractClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public abstract void send(Object t);

    public void close() {

    }

}
