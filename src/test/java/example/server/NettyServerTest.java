package example.server;

public class NettyServerTest extends ServerTestBase {

    @Override
    public IServer createServer(int port) {
        return new NettyServer(port);
    }
}
