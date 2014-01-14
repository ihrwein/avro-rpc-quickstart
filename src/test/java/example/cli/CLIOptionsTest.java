package example.cli;

import example.util.TesterMode;
import junit.framework.Assert;
import org.junit.Test;

public class CLIOptionsTest {

    @Test
    public void testAddOption() {
        CLIOptions c = new CLIOptions();
        c.addOption("name", "value");
    }

    @Test
    public void testGetAddress() {
        CLIOptions c = new CLIOptions();
        String address = "1.2.3.4";

        c.addOption(CLIOptionNameBinding.ADDRESS, address);

        Assert.assertEquals(address, c.getAddress());
    }

    @Test
    public void testGetImplementation() {
        CLIOptions c = new CLIOptions();
        String impl = "json";

        c.addOption(CLIOptionNameBinding.IMPLEMENTATION, impl);
        Assert.assertEquals(impl, c.getImplementation());
    }

    @Test
    public void testGetMessageNumber() {
        CLIOptions c = new CLIOptions();
        Integer messageNumber = 123;

        c.addOption(CLIOptionNameBinding.MESSAGE_NUMBER, messageNumber);
        Assert.assertEquals(messageNumber, c.getMessageNumber());
    }

    @Test
    public void testGetMessageSize() {
        CLIOptions c = new CLIOptions();
        Integer messageSize = 100;

        c.addOption(CLIOptionNameBinding.MESSAGE_SIZE, messageSize);
        Assert.assertEquals(messageSize, c.getMessageSize());
    }

    @Test
    public void testGetPort() {
        CLIOptions c = new CLIOptions();
        Integer port = 30000;

        c.addOption(CLIOptionNameBinding.PORT, port);
        Assert.assertEquals(port, c.getPort());
    }

    @Test
    public void testGetThreads() {
        CLIOptions c = new CLIOptions();
        Integer threads = 3;

        c.addOption(CLIOptionNameBinding.THREADS, threads);
        Assert.assertEquals(threads, c.getThreads());
    }

    @Test
    public void testGetTesterMode() {
        CLIOptions c = new CLIOptions();
        TesterMode t = TesterMode.CLIENT;

        c.addOption(CLIOptionNameBinding.TESTERMODE, t);
        Assert.assertEquals(t, c.getTesterMode());
    }

    @Test
    public void testIsJsonClientBuffering() {
        CLIOptions c = new CLIOptions();

        c.addOption(CLIOptionNameBinding.IS_JSON_CLIENT_BUFFERING, Boolean.TRUE);
        Assert.assertEquals(Boolean.TRUE, c.isJsonClientBuffering());

        c.addOption(CLIOptionNameBinding.IS_JSON_CLIENT_BUFFERING, Boolean.FALSE);
        Assert.assertEquals(Boolean.FALSE, c.isJsonClientBuffering());

    }

    @Test
    public void testIsNettyClientBuffering() {
        CLIOptions c = new CLIOptions();

        c.addOption(CLIOptionNameBinding.NETTY_CLIENT_BUFFER_SIZE, new Integer(100));
        Assert.assertEquals(new Integer(100), c.getNettyClientBufferSize());
    }
}
