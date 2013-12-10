package example.util;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

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

        CommandLineOptions co = Utils.parseCommandLineOptions(options);

        Assert.assertEquals(TesterMode.CLIENT, co.getTesterMode());
        Assert.assertEquals(address, co.getAddress());
        Assert.assertEquals(msgnum.intValue(), co.getMessageNumber());
        Assert.assertEquals(threads.intValue(), co.getThreads());
        Assert.assertEquals(impl, co.getImplementation());
        Assert.assertEquals(port.intValue(), co.getPort());
        Assert.assertEquals(msgsize.intValue(), co.getMessageSize());
    }

    @Test
    public void testRoundUpTo100() {
        int param = 987;
        int res = Utils.roundUpTo100(param);
        Assert.assertEquals(1000, res);

        Assert.assertEquals(1000, Utils.roundUpTo100(1000));

    }

}
