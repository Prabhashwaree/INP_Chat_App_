package client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler>client = new ArrayList<>();
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    BufferedReader bufferedReader;

    public ClientHandler(Socket socket) {
        try{
            this.socket = socket;
            this.bufferedWriter= new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername =bufferedReader.readLine();
            client.add(this);

        }catch (IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void initialize() {
        new Thread(this::run).start();
    }
    public void broadcastMassage(String massageToSend){
        for(ClientHandler clientHandler : client){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(massageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        client.remove(this);
        broadcastMassage("Server"+clientUsername+"left");
    }


    public void closeEverything(Socket socket, BufferedReader bufferedReader , BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if(bufferedReader !=null){
                bufferedReader.close();
            }if(bufferedWriter !=null){
                bufferedWriter.close();
            }if(socket !=null){
                socket.close();
            }
        }catch (IOException e){

        }
    }


    public void run() {
        String massageFormClient;

        while(socket.isConnected()){
            try{
                massageFormClient=bufferedReader.readLine();
                broadcastMassage(massageFormClient);
            }catch(IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }
    }
}
