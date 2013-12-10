package example.server;

import example.util.Defaults;
import org.apache.avro.ipc.Server;

public interface IServer extends Server {

    public static final String DEFAULT_ADDRESS = Defaults.ADDRESS;

}
