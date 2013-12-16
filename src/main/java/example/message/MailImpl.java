package example.message;

import example.proto.Mail;
import example.proto.Message;
import org.apache.avro.util.Utf8;

public class MailImpl implements Mail {
    // in this simple example just return details of the message
    public Void send(Message message) {

        return null;
    }
}