package example.cli;

import junit.framework.Assert;
import org.apache.commons.cli.*;
import org.junit.Test;
import static example.cli.CLIOptionNameBinding.*;

public class CLIOptionBuilderTest {

    @Test
    public void testBuildOptions() throws ParseException {
        String address = "1.2.3.4";
        String impl = "json";

        String[] args = {toCLIOption(CLIENT), address, toCLIOption(IMPLEMENTATION), impl};

        CLIOptions o = checkBuildOptions(args);

        Assert.assertEquals(address, o.getAddress());
        Assert.assertEquals(impl, o.getImplementation());
    }

    @Test(expected = AlreadySelectedException.class)
    public void testClientAndServerExclusion() throws ParseException {
        String[] args = { toCLIOption(CLIENT), "12.2.3.4", toCLIOption(SERVER)};

        CLIOptions o = checkBuildOptions(args);
    }

    @Test
    public void testJsonBuffering() throws ParseException {
        String address = "1.2.3.4";
        String impl = "json";

        String[] args = {toCLIOption(CLIENT), address, toCLIOption(IMPLEMENTATION), impl, toCLIOption(IS_JSON_CLIENT_BUFFERING)};

        CLIOptions o = checkBuildOptions(args);

        Assert.assertEquals(address, o.getAddress());
        Assert.assertEquals(impl, o.getImplementation());
        Assert.assertTrue(o.isJsonClientBuffering());
    }

    @Test
    public void testNettyBuffering() throws ParseException {
        String address = "1.2.3.4";
        String impl = "netty";
        String bufferSize= "100";

        String[] args = {toCLIOption(CLIENT), address, toCLIOption(IMPLEMENTATION), impl, toCLIOption(NETTY_CLIENT_BUFFER_SIZE), bufferSize};

        CLIOptions o = checkBuildOptions(args);

        Assert.assertEquals(address, o.getAddress());
        Assert.assertEquals(impl, o.getImplementation());
        Assert.assertEquals(new Integer(bufferSize), o.getNettyClientBufferSize());
    }

    @Test(expected = RuntimeException.class)
    public void testNettyBufferingWithNotNettyImplementation() throws ParseException {
        String address = "1.2.3.4";
        String impl = "json";
        String bufferSize= "100";

        String[] args = {toCLIOption(CLIENT), address, toCLIOption(IMPLEMENTATION), impl, toCLIOption(NETTY_CLIENT_BUFFER_SIZE), bufferSize};

        CLIOptions o = checkBuildOptions(args);
        Integer n = o.getNettyClientBufferSize();
    }

    private CLIOptions checkBuildOptions(String[] args) throws ParseException {
        Options options = CLIOptionBuilder.buildOptions();

        CommandLineParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, args);
        CLIOptions opts = CLIOptionParser.parse(cmd);
        return opts;
    }
}
