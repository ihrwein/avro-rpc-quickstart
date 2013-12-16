package example.cli;

import example.Main;
import example.util.Defaults;
import example.util.TesterMode;
import org.apache.commons.cli.CommandLine;

import java.util.Arrays;

public class CLIOptionParser {

    public static CLIOptions parse(CommandLine options) {
        CLIOptions cliOptions = new CLIOptions();

        parseClientAndServer(options, cliOptions);
        tryParseThreads(options, cliOptions);
        tryParsePort(options, cliOptions);
        tryParseMessageSize(options, cliOptions);
        tryParseMessageNumber(options, cliOptions);
        parseImplementation(options, cliOptions);
        parseJsonBuffering(options, cliOptions);

        return  cliOptions;
    }

    private static void parseJsonBuffering(CommandLine options, CLIOptions cliOptions) {
        String implementation = (String) cliOptions.getImplementation();

        Boolean jsonBuffering = "json".equals(implementation) ?  options.hasOption(CLIOptionNameBinding.IS_JSON_CLIENT_BUFFERING) : Boolean.FALSE;

        cliOptions.addOption(CLIOptionNameBinding.IS_JSON_CLIENT_BUFFERING, jsonBuffering);
    }

    private static void tryParseMessageNumber(CommandLine options, CLIOptions cliOptions) {
        TesterMode testerMode = cliOptions.getTesterMode();

        if (testerMode == TesterMode.CLIENT) {
            tryParseIntegerOption(CLIOptionNameBinding.MESSAGE_NUMBER, options, cliOptions, Defaults.MESSAGE_NUMBER);
        }
    }

    private static void tryParseMessageSize(CommandLine options, CLIOptions cliOptions) {
        TesterMode testerMode = cliOptions.getTesterMode();

        if (testerMode == TesterMode.CLIENT) {
            tryParseIntegerOption(CLIOptionNameBinding.MESSAGE_SIZE, options, cliOptions, Defaults.MESSAGE_SIZE);
        }
    }

    private static void tryParsePort(CommandLine options, CLIOptions cliOptions) {
        tryParseIntegerOption(CLIOptionNameBinding.PORT, options, cliOptions, Defaults.PORT);
    }

    private static void tryParseThreads(CommandLine options, CLIOptions cliOptions) {
        tryParseIntegerOption(CLIOptionNameBinding.THREADS, options, cliOptions, Runtime.getRuntime().availableProcessors());
    }

    private static void tryParseIntegerOption(String optionName, CommandLine options, CLIOptions cliOptions, int defaultValue) {
        int result;
        try {
            String t = options.getOptionValue(optionName);
            result = Integer.parseInt(t);
            cliOptions.addOption(optionName, new Integer(result));
        } catch (NumberFormatException e) {
            cliOptions.addOption(optionName, new Integer(defaultValue));
        }
    }

    private static void parseClientAndServer(CommandLine options, CLIOptions cliOptions) {
        String client = options.getOptionValue(CLIOptionNameBinding.CLIENT, null);
        String server = options.getOptionValue(CLIOptionNameBinding.SERVER, null);

        TesterMode testerMode = client != null ? TesterMode.CLIENT : TesterMode.SERVER;

        String address = testerMode == TesterMode.CLIENT ? client  : server;

        cliOptions.addOption("__testermode__", testerMode);

        cliOptions.addOption(CLIOptionNameBinding.ADDRESS, address);
    }

    private static void parseImplementation(CommandLine options, CLIOptions cliOptions) {
        String impl = options.getOptionValue("impl");

        int index = Arrays.asList(Main.IMPLEMENTATIONS).indexOf(impl);

        if (index < 0)
            impl = Defaults.IMPLEMENTATION;

        cliOptions.addOption("impl", impl);
    }
}
