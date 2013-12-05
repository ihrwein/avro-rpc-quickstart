package example.server.json;

import example.server.IServer;

public class JsonServerAdapter implements IServer {

    private JsonServer delegate;
    private Thread delegateThread;
    boolean isStarted;

    public JsonServerAdapter(int port) {
        isStarted = false;
        delegate = new JsonServer(port);
    }

    @Override
    public int getPort() {
        return delegate.getPort();
    }

    @Override
    public void start() {
        if (!isStarted) {
            delegateThread = new Thread(delegate);
            delegateThread.start();
            isStarted = true;
        }
    }

    @Override
    public void close() {
        if (isStarted)
            delegate.close();
    }

    @Override
    public void join() throws InterruptedException {
        if (isStarted)
            delegateThread.join();
    }
}
