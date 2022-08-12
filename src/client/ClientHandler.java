package client;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{

    Socket socket = new Socket();
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    BufferedReader bufferedReader;

    public ClientHandler() {
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
