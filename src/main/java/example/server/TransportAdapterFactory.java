package example.server;

import example.cli.ICLIOptions;
import example.client.AbstractClient;
import example.client.HttpClient;
import example.client.JsonClient;
import example.client.NettyClient;
import example.server.HttpServer;
import example.server.IServer;
import example.server.NettyServer;
import example.server.json.JsonServer;
import example.server.json.JsonServerAdapter;
import example.util.Defaults;

import java.io.IOException;

public class TransportAdapterFactory {

    public static IServer createServer(ICLIOptions options) throws IOException {
        return createServer(options.getImplementation(), options.getPort());
    }

    public static IServer createServer(String implementation, int port) throws IOException {
        if ("netty".equals(implementation)) {
            return new NettyServer(port);
        }
        else if ("http".equals(implementation)) {
            IServer  s = new HttpServer(port);
            s.start();
            return s;
        }
        else if ("json".equals(implementation)) {
            IServer s =  new JsonServerAdapter(port);
            s.start();
            return s;
        }

        return null;
    }

    public static AbstractClient createClient(ICLIOptions options) {
        return createClient(options.getImplementation(), options.getAddress(), options.getPort(), options.isJsonClientBuffering(), options);
    }

    public static AbstractClient createClient(String implementation, String address, int port, boolean buffering, ICLIOptions options) {
        if ("netty".equals(implementation)) {
            return new NettyClient(options);
        }
        else if ("http".equals(implementation)) {
            return new HttpClient(address, port);
        }
        else if ("json".equals(implementation)) {
            return new JsonClient(address, port, buffering);
        }

        return null;
    }
}
