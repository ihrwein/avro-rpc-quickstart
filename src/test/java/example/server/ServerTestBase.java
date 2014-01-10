package example.server;

import example.util.TestUtil;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public abstract class ServerTestBase {

    public IServer createServer(int port) {
        return null;
    }

    @Test
    public void testGetPort() throws Exception {
        int port = 6001;
        IServer s = createServer(port);
        s.start();
        Assert.assertEquals(port, s.getPort());
        s.close();
    }

    @Test
    public void testStart() throws Exception {
        int port = 6001;
        Assert.assertTrue(TestUtil.isPortAvailable(port));
        IServer s = createServer(port);
        s.start();
        Assert.assertFalse(TestUtil.isPortAvailable(port));
        s.close();
    }
}