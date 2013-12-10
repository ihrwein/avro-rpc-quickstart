package example.server.json;

import example.server.IServer;
import example.util.Defaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JsonServer implements Runnable {

    private ServerSocket serverSocket;
    private  int port;

    public JsonServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Socket clientSocket = null;
        try {
            int clientNumber = 0;
            while (true && !serverSocket.isClosed()) {
                clientSocket = serverSocket.accept();
                System.out.println("New client: #" + clientNumber);
                clientNumber++;
                ClientHandler ch = new ClientHandler(clientSocket);
                Thread t = new Thread(ch);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
