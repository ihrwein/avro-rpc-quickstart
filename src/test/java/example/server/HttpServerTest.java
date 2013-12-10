package example.server;

import example.util.TestUtil;
import junit.framework.Assert;
import org.junit.Test;

public class HttpServerTest extends ServerTestBase{

    @Override
    public IServer createServer(int port) {
       return new HttpServer(port);
    }
}
