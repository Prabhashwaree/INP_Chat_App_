package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
   
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("server started..");
        Server server = new Server(serverSocket);
        server.serverSocket();
    }

    private void serverSocket() {
    }
}
