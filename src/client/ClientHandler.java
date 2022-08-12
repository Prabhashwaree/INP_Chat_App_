package client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{

    private ArrayList<ClientHandler> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    ObjectOutputStream objectOutputStream = null;
    ObjectInputStream objectInputStream = null;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
       try {
           this.socket = socket;
           this.clients = clients;
           this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           this.writer = new PrintWriter(socket.getOutputStream(),true);
       } catch (IOException exception) {
           exception.printStackTrace();
       }
    }


    public void  runner(){
        try{
            String massage;
            while ((massage = reader.readLine()) !=null){
               if(massage.equals("Exits")){
                   return;
               }
               for (ClientHandler clientHandler : clients){
                   clientHandler.writer.println(massage);
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                writer.close();
                socket.close();
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
