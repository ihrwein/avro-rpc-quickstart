package example.server;

import example.MailImpl;
import example.proto.Mail;
import org.apache.avro.ipc.specific.SpecificResponder;

import java.net.InetSocketAddress;

public class NettyServer implements IServer {

    private org.apache.avro.ipc.NettyServer delegate;

    public NettyServer(int port) {
        delegate = new org.apache.avro.ipc.NettyServer(new SpecificResponder(Mail.class, new MailImpl()), new InetSocketAddress(port));
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
