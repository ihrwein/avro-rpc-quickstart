package example.server.json;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Socket socket = null;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[65536];
            int n = 1;
            while (true) {
                n = is.read(buffer, 0, 63536);
                if (n < 0)
                    break;
            }
            is.close();
            this.socket.close();
        }
        catch (IOException e) {
            System.err.println(e.toString());
        }

    }
}