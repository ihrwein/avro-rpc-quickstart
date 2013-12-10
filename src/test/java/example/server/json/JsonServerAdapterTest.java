package example.server.json;

import example.server.IServer;
import example.server.ServerTestBase;

public class JsonServerAdapterTest extends ServerTestBase {

    @Override
    public IServer createServer(int port) {
        return new JsonServerAdapter(port);
    }
}
