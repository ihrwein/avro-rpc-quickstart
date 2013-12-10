package example.util;

import example.Main;
import org.junit.Assert;
import org.junit.Test;

public class CommandLineOptionsTest {
    @Test
    public void testGetThreads() throws Exception {
        String args[] = { "-threads", "7", "-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(7, o.getThreads());
    }

    @Test
    public void testDefaultThreads() {
        String args[] = {"-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertTrue(o.getThreads() > 0);
    }

    @Test
    public void testGetAddress() throws Exception {
        String args[] = { "-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals("127.0.0.1", o.getAddress());
    }

    @Test
    public void testGetPort() throws Exception {
        String args[] = { "-port", "12345", "-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(12345, o.getPort());
    }

    @Test
    public void testDefaultPort() {
        String args[] = {"-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(Defaults.PORT, o.getPort());
    }

    @Test
    public void testGetImplementation() throws Exception {
        for (String impl: Main.IMPLEMENTATIONS) {
            String args[] = { "-impl", impl, "-client", "127.0.0.1"};
            CommandLineOptions o = Utils.parseCommandLineOptions(args);
            Assert.assertEquals(impl, o.getImplementation());
        }
    }

    @Test
    public void testDefaultImplementation() {
        String args[] = {"-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertNotNull(o.getImplementation());
    }

    @Test
    public void testGetMessageSize() throws Exception {
        String args[] = { "-msgsize", "1024", "-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(1024, o.getMessageSize());
    }

    @Test
    public void testDefaultMessageSize() {
        String args[] = { "-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(Defaults.MESSAGE_SIZE, o.getMessageSize());
    }

    @Test
    public void testGetMessageNumber() throws Exception {
        String args[] = {"-msgnum", "1024" , "-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(1100, o.getMessageNumber());
    }

    @Test
    public void testDefaultMessageNumber() {
        String args[] = {"-client", "127.0.0.1"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(Defaults.MESSAGE_NUMBER, o.getMessageNumber());
    }

    @Test
    public void testClientTesterMode() throws Exception {
        String args[] = {"-client", "127.0.0.2"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(TesterMode.CLIENT, o.getTesterMode());
        Assert.assertEquals("127.0.0.2", o.getAddress());
    }

    @Test
    public void testServerTesterMode() throws Exception {
        String args[] = {"-server"};
        CommandLineOptions o = Utils.parseCommandLineOptions(args);
        Assert.assertEquals(TesterMode.SERVER, o.getTesterMode());
    }
}
