package example.util;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static CommandLineOptions parseCommandLineOptions(String[] args) {
        Options options = buildCommandLineOptions();

        CommandLineParser parser = new GnuParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            CommandLineOptions opts = new CommandLineOptions(cmd);
            return opts;
        } catch (ParseException e) {
            System.err.println( "Parsing failed.  Reason: " + e.getMessage() );
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "java-perftest", options);
        }

        return null;
    }

    private static Options buildCommandLineOptions() {
        Options options = new Options();

        OptionGroup clientServerGroup = new OptionGroup();

        clientServerGroup.addOption(OptionBuilder.withArgName("IP address").hasArg().withDescription("client mode").create("client"));
        clientServerGroup.addOption(new Option("s", "server", false, "server mode" ));
        clientServerGroup.setRequired(true);
        options.addOptionGroup(clientServerGroup);

        options.addOption(OptionBuilder.withArgName("number").hasArg().withDescription("number of threads").withType(Integer.class).create("threads"));
        options.addOption(OptionBuilder.withArgName("implementation").hasArg().withDescription("netty|http|json").create("impl"));
        options.addOption(OptionBuilder.withArgName("port number").hasArg().withDescription("the number of port to connect or bind on").withType(Integer.class).create("port"));
        options.addOption(OptionBuilder.withArgName("size").hasArg().withDescription("the size of messages in bytes").withType(Integer.class).create("msgsize"));
        options.addOption(OptionBuilder.withArgName("number").hasArg().withDescription("the number of messages").withType(Integer.class).create("msgnum"));
        return options;
    }

    public static void block() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try {
            while ((s = in.readLine()) != null && s.length() != 0)
                Thread.sleep(100);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int roundUpTo100(int n) {
        int rem = n % 100;

        if (rem == 0)
            return n;

        return n + (100 - rem);
    }

}
