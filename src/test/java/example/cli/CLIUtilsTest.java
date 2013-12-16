package example.cli;

import example.util.TesterMode;
import org.junit.Assert;
import org.junit.Test;

public class CLIUtilsTest {

    @Test
    public void testParseCommandLineOptions() {
        String address = "1.2.4.4";
        Integer msgnum = 1300;
        Integer threads = 12;
        String impl = "netty";
        Integer port = 623;
        Integer msgsize = 2045;

        String options[] = {"-client", address, "-msgnum", msgnum.toString(), "-threads", threads.toString(),
                "-impl", "netty", "-port", port.toString(), "-msgsize", msgsize.toString()};

        CLIOptions co = CLIUtils.parseCommandLineOptions(options);

        Assert.assertEquals(TesterMode.CLIENT, co.getTesterMode());
        Assert.assertEquals(address, co.getAddress());
        Assert.assertEquals(msgnum, co.getMessageNumber());
        Assert.assertEquals(threads, co.getThreads());
        Assert.assertEquals(impl, co.getImplementation());
        Assert.assertEquals(port, co.getPort());
        Assert.assertEquals(msgsize, co.getMessageSize());
    }
}
