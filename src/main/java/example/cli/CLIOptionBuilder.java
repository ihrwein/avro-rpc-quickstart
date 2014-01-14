package example.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class CLIOptionBuilder {

    public static Options buildOptions() {
        Options options = new Options();

        OptionGroup clientServerGroup = new OptionGroup();

        clientServerGroup.addOption(OptionBuilder.withArgName("IP address").hasArg().withDescription("client mode").create(CLIOptionNameBinding.CLIENT));
        clientServerGroup.addOption(new Option("s", CLIOptionNameBinding.SERVER, false, "server mode"));
        clientServerGroup.setRequired(true);
        options.addOptionGroup(clientServerGroup);

        options.addOption(OptionBuilder.withArgName("number").hasArg().withDescription("number of threads").withType(Integer.class).create(CLIOptionNameBinding.THREADS));
        options.addOption(OptionBuilder.withArgName("implementation").hasArg().withDescription("netty|http|json").create(CLIOptionNameBinding.IMPLEMENTATION));
        options.addOption(OptionBuilder.withArgName("port number").hasArg().withDescription("the number of port to connect or bind on").withType(Integer.class).create(CLIOptionNameBinding.PORT));
        options.addOption(OptionBuilder.withArgName("size").hasArg().withDescription("the size of messages in bytes").withType(Integer.class).create(CLIOptionNameBinding.MESSAGE_SIZE));
        options.addOption(OptionBuilder.withArgName("number").hasArg().withDescription("the number of messages").withType(Integer.class).create(CLIOptionNameBinding.MESSAGE_NUMBER));
        options.addOption(new Option("jb", CLIOptionNameBinding.IS_JSON_CLIENT_BUFFERING, false, "json buffering"));
        options.addOption(OptionBuilder.withArgName("buffer size").hasArg().withDescription("the number of messages to buffer").withType(Integer.class).create(CLIOptionNameBinding.NETTY_CLIENT_BUFFER_SIZE));
        return options;
    }
}