package example.server.json;

import example.client.JsonClient;
import example.server.IServer;
import example.server.ServerTestBase;
import org.junit.Test;

public class JsonServerAdapterTest extends ServerTestBase {

    @Override
    public IServer createServer(int port) {
        return new JsonServerAdapter(port);
    }

    @Test
    public void testCommunication() throws InterruptedException {
        JsonServerAdapter s = new JsonServerAdapter(1234);
        s.start();
        JsonClient c   = new JsonClient("127.0.0.1", 1234);
        for (int i = 0; i < 100000; i++) {
            c.send("{ \"to\" : \"t\", \"from\" : \"f\", \"body\" : \"b\"}");
        }

        c.close();
        s.close();
        s.join();
    }

}
