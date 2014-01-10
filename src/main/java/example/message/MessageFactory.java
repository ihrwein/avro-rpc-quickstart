package example.message;

import example.cli.ICLIOptions;
import example.proto.Message;
import org.apache.avro.util.Utf8;

public class MessageFactory {

    private static final int DEFAULT_CONTENT_SIZE = 8;
    private static final String RECIPIENT = "Bob";
    private static final String SENDER = "Alice";

    public static Object createMessage(ICLIOptions options) {
        return createMessage(options.getImplementation(), options.getMessageSize());
    }

    public static Object createMessage(String implementation, int size) {
        if ("netty".equals(implementation) || "http".equals(implementation)) {
            return createAvroMessage(size);
        } else if ("json".equals(implementation)) {
            return createJsonMessage(size);
        }

        return null;
    }

    public static Message createAvroMessage(int size) {
        Message message = new Message();
        message.setTo(new Utf8(SENDER));
        message.setFrom(new Utf8(RECIPIENT));

        String padding = createPadding(size - DEFAULT_CONTENT_SIZE);
        message.setBody(new Utf8(padding));

        return message;
    }

    private static String createPadding(int size) {
        StringBuilder builder = new StringBuilder(size);

        for(int i = 0; i < size; i++){
            builder.append("0");
        }

        return builder.toString();
    }

    public static Object createJsonMessage(int size) {
        String padding = createPadding(size - DEFAULT_CONTENT_SIZE);
        return new JsonMessage(SENDER, RECIPIENT, padding);
    }
}
