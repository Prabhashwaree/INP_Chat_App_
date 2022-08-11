package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static Socket accept = null;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("server started..");
        accept = serverSocket.accept();
        System.out.println("client cnnected...");

    }
}
