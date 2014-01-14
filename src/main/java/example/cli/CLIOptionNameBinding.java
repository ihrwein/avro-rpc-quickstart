package example.cli;

public class CLIOptionNameBinding {

    public static final String IMPLEMENTATION = "impl";
    public static final String CLIENT = "client";
    public static final String SERVER = "server";
    public static final String ADDRESS = "address";
    public static final String PORT = "port";
    public static final String MESSAGE_NUMBER = "msgnum";
    public static final String MESSAGE_SIZE = "msgsize";
    public static final String THREADS = "threads";
    public static final String TESTERMODE = "__testermode__";
    public static final String IS_JSON_CLIENT_BUFFERING = "client.json.buffering";
    public static final String NETTY_CLIENT_BUFFER_SIZE = "netty_buffer_size";

    public static String toCLIOption(String name) {
        return "-" + name;
    }

}
