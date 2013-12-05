package example;

import example.proto.Message;
import org.apache.avro.util.Utf8;

public class MessageFactory {

    public static Object createMessage(String implementation, int size) {
        if ("netty".equals(implementation) || "http".equals(implementation)) {
            return createAvroMessage(size);
        } else if ("json".equals(implementation)) {
            return createJsonMessage(size);
        }

        return null;
    }

    private static Message createAvroMessage(int size) {
        int minSize = 8;
        Message message = new Message();
        message.setTo(new Utf8("Bob"));
        message.setFrom(new Utf8("Alice"));

        String padding = createPadding(size, minSize);

        message.setBody(new Utf8(padding));

        return message;
    }

    private static String createPadding(int size, int minSize) {
        StringBuilder builder = new StringBuilder(size);

        if (size > minSize) {
            for(int i = 0; i < size - minSize; i++){
                builder.append("0");
            }
        }

        return builder.toString();
    }

    private static String createJsonMessage(int size) {
        int minSize = 8;
        String padding = createPadding(size, minSize);
        JsonMessage m = new JsonMessage("Bob", "Alice", padding);
        return m.toString();
    }

}
