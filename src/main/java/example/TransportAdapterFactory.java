package example;

import example.client.AbstractClient;
import example.client.HttpClient;
import example.client.JsonClient;
import example.client.NettyClient;
import example.server.HttpServer;
import example.server.IServer;
import example.server.NettyServer;
import example.server.json.JsonServer;
import example.server.json.JsonServerAdapter;

import java.io.IOException;

public class TransportAdapterFactory {

    public static final int DEFAULT_PORT = 65111;

    public static IServer createServer(String implementation) throws IOException {
        return createServer(implementation, DEFAULT_PORT);
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

    public static AbstractClient createClient(String implementation) throws IOException {
        return createClient(implementation, DEFAULT_PORT);
    }

    public static AbstractClient createClient(String implementation, int port) throws IOException {
        if ("netty".equals(implementation)) {
            return new NettyClient(port);
        }
        else if ("http".equals(implementation)) {
            return new HttpClient(port);
        }
        else if ("json".equals(implementation)) {
            return new JsonClient(port);
        }

        return null;
    }
}
