package example.client;

import org.apache.avro.ipc.NettyTransceiver;

import java.io.IOException;
import java.net.InetSocketAddress;

public class NettyClient extends AvroClient{

    public NettyClient(int port) {
        super(port);
        NettyTransceiver delegate = null;
        try {
            delegate = new NettyTransceiver(new InetSocketAddress(port));
            setDelegate(delegate);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
