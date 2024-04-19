package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;
    private volatile boolean running;

    public MyServer(int port, ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        try {
            this.serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create server socket on port " + port, e);
        }
    }

    public void start() {
        running = true;
        new Thread(() -> {
            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                } catch (IOException e) {
                    if (running) {
                        System.out.println("Error accepting client connection: " + e.getMessage());
                    }
                }
            }
        }).start();
    }

    private void handleClient(Socket clientSocket) {
        try {
            clientHandler.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error handling client connection: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    public void close() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing server socket: " + e.getMessage());
        }
    }
}