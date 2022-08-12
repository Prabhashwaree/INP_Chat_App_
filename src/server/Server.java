package server;

import client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
  /*  public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }*/

  /*  public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("server started..");
        Server server = new Server(serverSocket);
        server.serverSocket();
    }*/



    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket accept;

        while (true){
            accept =serverSocket.accept();
            System.out.println("Connected");
            ClientHandler clientHandler = new ClientHandler(accept, clients);
            clients.add(clientHandler);
            clientHandler.start();
        }
    }

}
