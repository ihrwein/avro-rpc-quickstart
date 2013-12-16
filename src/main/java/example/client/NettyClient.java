package example.client;

import org.apache.avro.ipc.NettyTransceiver;

import java.io.IOException;
import java.net.InetSocketAddress;

public class NettyClient extends AvroClient{

    public NettyClient(int port) {
        this(DEFAULT_ADDRESS, port);
    }

    public NettyClient(String address, int port) {
        super(address, port);
        NettyTransceiver delegate = null;
        try {
            delegate = new NettyTransceiver(new InetSocketAddress(address, port));
            setDelegate(delegate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
