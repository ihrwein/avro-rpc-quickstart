package example.message;

import junit.framework.Assert;
import org.junit.Test;

public class MessageFactoryTest {


    @Test
    public void testCreateNettyMessage() throws Exception {
        Object m = MessageFactory.createMessage("netty", 200);
        Assert.assertNotNull(m);
    }

    @Test
    public void testCreateHttpMessage() throws Exception {
        Object m = MessageFactory.createMessage("http", 200);
        Assert.assertNotNull(m);
    }

    @Test
    public void testCreateJsonMessage() throws Exception {
        String m = (String) MessageFactory.createMessage("json", 200);
        Assert.assertNotNull(m);
        Assert.assertTrue(m.charAt(0) == '{');
    }
}
