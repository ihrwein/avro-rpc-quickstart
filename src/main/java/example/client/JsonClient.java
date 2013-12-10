package example.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class JsonClient extends AbstractClient {

    private Socket clientSocket;
    private DataOutputStream os;

    public JsonClient(String address, int port) {
        super(address, port);

        clientSocket = new Socket();
        try {
            clientSocket.connect(new InetSocketAddress(address, port));
            os = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Object t) {
        send((String) t);
    }

    public void send(String t) {
        try {
            os.writeBytes(t);
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
