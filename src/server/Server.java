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

    /*private void serverSocket() {
        try{
            while (!serverSocket.isClosed()) {
                Socket socket = null;

                socket = serverSocket.accept();

                System.out.println("client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }*/

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket accept;

        while (true){
            System.out.println("Waiting for Client ...");
            accept =serverSocket.accept();
            System.out.println("Connected");


            ClientHandler clientHandler = new ClientHandler(accept, clients);
            clients.add(clientHandler);
            clientHandler.start();
        }

    }


}
