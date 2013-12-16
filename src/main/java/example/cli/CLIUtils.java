package example.cli;

import org.apache.commons.cli.*;

public class CLIUtils {

    public static CLIOptions parseCommandLineOptions(String[] args) {
        Options options = CLIOptionBuilder.buildOptions();

        CommandLineParser parser = new GnuParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            CLIOptions opts = CLIOptionParser.parse(cmd);
            return opts;
        } catch (ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java-perftest", options);
        }

        return null;
    }
}