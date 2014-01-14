package example.client;

import example.cli.ICLIOptions;
import example.proto.Message;
import org.apache.avro.ipc.NettyTransceiver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class NettyClient extends AvroClient{

    private ICLIOptions options;
    private Integer bufferSize;
    private List<Message> bufferedMessages;

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

    public NettyClient(ICLIOptions options) {
        this(options.getAddress(), options.getPort());
        this.options = options;
        bufferSize = options.getNettyClientBufferSize();
        bufferedMessages = new ArrayList<>(bufferSize);
    }

    @Override
    public void send(Message m) {
        if (bufferSize > 0) {
            bufferedMessages.add(m);

            if (bufferedMessages.size() == bufferSize) {
                sendBufferedMessages();
            }
        }
        else {
            super.send(m);
        }
    }

    private void sendBufferedMessages() {
        sendMore(bufferedMessages);
        //System.out.println("Sent " + bufferedMessages.size() + " messages");
        bufferedMessages.clear();
    }

    @Override
    public void close() {
        sendBufferedMessages();
        super.close();
    }
}
