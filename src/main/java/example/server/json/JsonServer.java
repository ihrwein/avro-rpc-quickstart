package example.server.json;

import example.server.IServer;
import example.util.Defaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JsonServer implements Runnable {

    private ServerSocket serverSocket;
    private  int port;
    private ExecutorService pool;

    public JsonServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            pool = Executors.newFixedThreadPool(16);
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
                pool.execute(new ClientHandler(clientSocket));
            }
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
