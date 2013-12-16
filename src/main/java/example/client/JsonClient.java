package example.client;

import example.util.Utils;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class JsonClient extends AbstractClient {

    private Socket clientSocket;
    private OutputStream os;
    private boolean buffering;

    public JsonClient(String address, int port, boolean buffering) {
        super(address, port);

        this.buffering = buffering;
        clientSocket = new Socket();
        try {
            clientSocket.connect(new InetSocketAddress(address, port));

            initOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initOutputStream() throws IOException {
        OutputStream socketOutputStream =  clientSocket.getOutputStream();

        if (buffering)
            os = new BufferedOutputStream(socketOutputStream);
        else
            os = new DataOutputStream(socketOutputStream);
    }

    public JsonClient(String address, int port) {
        this(address, port, true);
    }

    public void send(Object t) {
        send(t.toString());
    }

    public void send(String t) {
        send(t.getBytes());
    }

    public void send(byte[] b) {
        try {
            os.write(getPreamble(b));
            os.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getPreamble(byte [] b)
    {
        int frameSize = b.length;
        byte[] framePreamble = Utils.toBytes(frameSize);

        return  framePreamble;
    }

    @Override
    public void close() {
        try {
            os.flush();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
