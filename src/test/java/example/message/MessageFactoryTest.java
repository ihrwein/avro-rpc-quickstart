package example.message;

import junit.framework.Assert;
import org.junit.Test;

public class MessageFactoryTest {

    @Test
    public void testCreateAvroMessage() throws Exception {
        Object m = MessageFactory.createAvroMessage(200);
        Assert.assertNotNull(m);
    }

    @Test
    public void testCreateJsonMessage() throws Exception {
        int messageSize = 200;

        JsonMessage m = (JsonMessage) MessageFactory.createJsonMessage(messageSize);
        Assert.assertNotNull(m);
        Assert.assertTrue(m.toString().charAt(0) == '{');
        int realSize = m.toString().length();
    }
}
