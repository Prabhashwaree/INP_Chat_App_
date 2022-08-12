package client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{


    private Socket socket;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    BufferedReader bufferedReader;

    public ClientHandler(Socket socket) {
//        try {
//            this.socket = socket;
//        }catch (){
//
//        }
    }

    public void initialize() {
        new Thread(this::run).start();
    }


    public void run() {
        try {

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStreamReader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
