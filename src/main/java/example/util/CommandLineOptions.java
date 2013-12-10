package example.util;

import example.Main;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.util.Arrays;

public class CommandLineOptions {

    private CommandLine options;
    private TesterMode testerMode;
    private int threads;
    private String address;
    private int port;
    private int messageSize;
    private int messageNumber;
    private String implementation;

    public CommandLineOptions(CommandLine options) {
        this.options = options;

        parseClientAndServer();
        tryParseThreads();
        tryParsePort();
        tryParseMessageSize();
        tryParseMessageNumber();
        parseImplementation();
    }

    private void tryParseMessageNumber() {
        if (testerMode == TesterMode.CLIENT) {
            messageNumber = tryParseIntegerOption("msgnum", Defaults.MESSAGE_NUMBER);
            messageNumber = Utils.roundUpTo100(messageNumber);
        }
    }

    private void tryParseMessageSize() {
        if (testerMode == TesterMode.CLIENT) {
            messageSize = tryParseIntegerOption("msgsize", Defaults.MESSAGE_SIZE);
        }
    }

    private void tryParsePort() {
        port = tryParseIntegerOption("port", Defaults.PORT);
    }

    private void tryParseThreads() {
        threads = tryParseIntegerOption("threads", Runtime.getRuntime().availableProcessors());
    }

    private int tryParseIntegerOption(String optionName, int defaultValue) {
        int result;
        try {
            String t = options.getOptionValue(optionName);
            result = Integer.parseInt(t);
        } catch (NumberFormatException e) {
            result = defaultValue;
        }

        return result;
    }

    private void parseClientAndServer() {
        String client = options.getOptionValue("client", null);
        String server = options.getOptionValue("server", null);

        testerMode = client != null ? TesterMode.CLIENT : TesterMode.SERVER;

        address = testerMode == TesterMode.CLIENT ? client : server;
    }

    private void parseImplementation() {
        String impl = this.options.getOptionValue("impl");

        int index = Arrays.asList(Main.IMPLEMENTATIONS).indexOf(impl);

        if (index < 0)
            impl = Defaults.IMPLEMENTATION;

        implementation = impl;
    }

    public int getThreads() {
        return threads;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public String getImplementation() {
        return implementation;
    }

    public int getMessageSize() {
        return messageSize;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public TesterMode getTesterMode() {
        return testerMode;
    }
}
