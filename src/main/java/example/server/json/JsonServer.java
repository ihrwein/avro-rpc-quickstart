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
    private volatile boolean isClosed;

    public JsonServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            pool = Executors.newFixedThreadPool(16);
            isClosed = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public void close() {
        if (!isClosed)  {
            isClosed = true;
        // to close the socket, we should take it out from accept()
            try {
                new Socket("127.0.0.1", port).close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void run() {
        Socket clientSocket = null;
        try {
            int clientNumber = 0;
            while (!isClosed && !serverSocket.isClosed()) {
                clientSocket = serverSocket.accept();
                System.out.println("New client: #" + clientNumber);
                clientNumber++;
                pool.execute(new ClientHandler(clientSocket));
            }
            pool.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
