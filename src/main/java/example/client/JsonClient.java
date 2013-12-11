package example.client;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class JsonClient extends AbstractClient {

    private Socket clientSocket;
    private BufferedOutputStream os;

    public JsonClient(String address, int port) {
        super(address, port);

        clientSocket = new Socket();
        try {
            clientSocket.connect(new InetSocketAddress(address, port));
            os = new BufferedOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Object t) {
        send((String) t);
    }

    public void send(String t) {
        send(t.getBytes());
    }

    public void send(byte[] b) {
        try {
            os.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            os.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
