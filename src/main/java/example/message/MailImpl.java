package example.message;

import example.proto.Mail;
import example.proto.Message;
import org.apache.avro.util.Utf8;

public class MailImpl implements Mail {
    // in this simple example just return details of the message
    public Utf8 send(Message message) {
        int minMessageLenth = message.getTo().length() + message.getFrom().length() + message.getBody() .length();
        StringBuilder b = new StringBuilder(minMessageLenth + 20);
        b.append("to ").append(message.getTo()).append(" from ").append(message.getFrom()).append(" body ").append(message.getBody());

        return new Utf8(b.toString());
    }
}