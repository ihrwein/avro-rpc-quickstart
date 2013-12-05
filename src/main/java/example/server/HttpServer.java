package example.server;

import example.MailImpl;
import example.proto.Mail;
import org.apache.avro.ipc.specific.SpecificResponder;

import java.io.IOException;

public class HttpServer implements IServer {

    private org.apache.avro.ipc.HttpServer delegate;

    public HttpServer(int port) {
        try {
            delegate = new org.apache.avro.ipc.HttpServer(new SpecificResponder(Mail.class, new MailImpl()), port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getPort() {
        return  delegate.getPort();
    }

    @Override
    public void start() {
        delegate.start();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public void join() throws InterruptedException {
        delegate.join();
    }
}
