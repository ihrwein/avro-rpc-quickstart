package example.server.json;


import example.util.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler implements Runnable {

    Socket socket = null;
    JSONParser parser;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.parser = new JSONParser();
    }

    @Override
    public void run() {
        DataInputStream is = null;
        try {
            is = new DataInputStream(socket.getInputStream());
            byte[] buffer = new byte[65536];
            while (true) {
                int p = is.readInt();
                is.readFully(buffer, 0, p);
                deserialize(buffer, p);
            }

        }
        catch (IOException e) {

        }
        finally {
            if (is != null) {
                try {
                    is.close();
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private void deserialize(byte[] msg, int n) {
        try {
            String message = new String(Arrays.copyOfRange(msg, 0, n));
            JSONObject o = (JSONObject) parser.parse(message);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}