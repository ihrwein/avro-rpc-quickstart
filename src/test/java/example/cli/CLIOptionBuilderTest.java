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

    private CLIOptions checkBuildOptions(String[] args) throws ParseException {
        Options options = CLIOptionBuilder.buildOptions();

        CommandLineParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, args);
        CLIOptions opts = CLIOptionParser.parse(cmd);
        return opts;
    }
}
