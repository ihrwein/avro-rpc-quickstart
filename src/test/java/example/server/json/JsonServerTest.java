package example.server.json;

import example.util.TestUtil;
import junit.framework.Assert;
import org.junit.Test;

public class JsonServerTest {

    @Test
    public void testGetPort() {
        int port = 2000;
        JsonServer s = new JsonServer(port);
        Assert.assertEquals(port, s.getPort());
        s.close();
    }

    @Test
    public void testStart() {
        int port = 2000;
        JsonServer s = new JsonServer(port);
        Thread t = new Thread(s);
        t.start();
        Assert.assertFalse(TestUtil.isPortAvailable(port));
        s.close();
        Assert.assertTrue(TestUtil.isPortAvailable(port));
    }
}
