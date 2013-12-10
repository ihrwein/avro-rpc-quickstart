package example.client;

import example.proto.Mail;
import example.proto.Message;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.Transceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;

import java.io.IOException;

public class AvroClient extends AbstractClient{

    protected Transceiver delegate;
    private Mail proxy;

    public AvroClient(String address, int port) {
        super(address, port);
    }

    @Override
    public void send(Object t) {
        send((Message) t);
    }

    protected void setDelegate(Transceiver t) {
        delegate = t;
        try {
            proxy = (Mail) SpecificRequestor.getClient(Mail.class, delegate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Message m) {
        try {
            proxy.send(m);
        } catch (AvroRemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            delegate.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
