package example.cli;

import example.Main;
import example.util.Defaults;
import example.util.TesterMode;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CLIOptionParserTest {
    @Test
    public void testGetThreads() throws Exception {
        String address = "127.0.0.1";
        String threads = "7";

        String args[] = { "-threads", threads, "-client", address};

        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(new Integer(threads), o.getThreads());
    }

    @Test
    public void testDefaultThreads() {
        String address = "127.0.0.1";

        String args[] = {"-client", address};

        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertTrue(o.getThreads() > 0);
    }

    @Test
    public void testGetAddress() throws Exception {
        String address = "127.0.0.1";

        String args[] = {"-client", address};

        CLIOptions o = CLIUtils.parseCommandLineOptions(args);

        Assert.assertEquals("127.0.0.1", o.getAddress());
    }

    @Test
    public void testGetPort() throws Exception {
        String port = "12345";
        String address = "127.0.0.1";

        String args[] = { "-port", port, "-client", address};

        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(new Integer(port), o.getPort());
    }

    @Test
    public void testDefaultPort() {
        String address = "127.0.0.1";
        String args[] = {"-client", address};

        CLIOptions o = CLIUtils.parseCommandLineOptions(args);

        Assert.assertEquals(new Integer(Defaults.PORT), o.getPort());
    }

    @Test
    public void testGetImplementation() throws Exception {
        String address = "127.0.0.1";

        for (String impl: Main.IMPLEMENTATIONS) {
            String args[] = { "-impl", impl, "-client", address};

            CLIOptions o = CLIUtils.parseCommandLineOptions(args);
            Assert.assertEquals(impl, o.getImplementation());
        }
    }

    @Test
    public void testDefaultImplementation() {
        String address = "127.0.0.1";

        String args[] = {"-client", address};

        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertNotNull(o.getImplementation());
    }

    @Test
    public void testGetMessageSize() throws Exception {
        String args[] = { "-msgsize", "1024", "-client", "127.0.0.1"};
        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(new Integer(1024), o.getMessageSize());
    }

    @Test
    public void testDefaultMessageSize() {
        String args[] = { "-client", "127.0.0.1"};
        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(new Integer(Defaults.MESSAGE_SIZE), o.getMessageSize());
    }

    @Test
    public void testGetMessageNumber() throws Exception {
        String args[] = {"-msgnum", "1024" , "-client", "127.0.0.1"};
        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(new Integer(1024), o.getMessageNumber());
    }

    @Test
    public void testDefaultMessageNumber() {
        String args[] = {"-client", "127.0.0.1"};
        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(new Integer(Defaults.MESSAGE_NUMBER), o.getMessageNumber());
    }

    @Test
    public void testClientTesterMode() throws Exception {
        String args[] = {"-client", "127.0.0.2"};
        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(TesterMode.CLIENT, o.getTesterMode());
        Assert.assertEquals("127.0.0.2", o.getAddress());
    }

    @Test
    public void testServerTesterMode() throws Exception {
        String args[] = {"-server"};
        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(TesterMode.SERVER, o.getTesterMode());
    }

    @Test
    public void testJsonBuffering() {
        String args[] = {"-client", "127.0.0.1", "-impl", "json",
                         CLIOptionNameBinding.toCLIOption(CLIOptionNameBinding.IS_JSON_CLIENT_BUFFERING)};
        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(true, o.isJsonClientBuffering());
    }

    @Test
    public void testNettyBufferSize() {
        String size = "1000";
        String args[] = {"-client", "127.0.0.1", "-impl", "netty",
                CLIOptionNameBinding.toCLIOption(CLIOptionNameBinding.NETTY_CLIENT_BUFFER_SIZE), size};

        CLIOptions o = CLIUtils.parseCommandLineOptions(args);
        Assert.assertEquals(new Integer(size), o.getNettyClientBufferSize());
    }


}
