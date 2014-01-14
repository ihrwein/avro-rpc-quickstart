package example.message;

import example.proto.Mail;
import example.proto.Message;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.util.Utf8;

import java.util.List;

public class MailImpl implements Mail {
    // in this simple example just return details of the message
    public Void send(Message message) {

        return null;
    }

    @Override
    public Void sendMore(List<Message> messages) throws AvroRemoteException {
        return null;
    }
}