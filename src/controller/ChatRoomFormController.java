package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatRoomFormController extends Thread {
    public TextArea txtArea;
    public Button btnCamara;
    public Button btnFile;
    public Button btnImoge;
    public TextField txtMessage;
    public AnchorPane chatRoomPane;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    private FileChooser fileChooser;
    private File filePath;

    public void initialize() {

        try{
             socket = new Socket("localhost",8000);
             System.out.println("socket ek awaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!");
             reader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
             writer = new PrintWriter(socket.getOutputStream(),true);
             this.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




        public void txtAreaOnAction(MouseEvent mouseEvent) {
    }

    public void btnCamaraOnAction(ActionEvent actionEvent) {
    }

    public void btnFileOnAction(ActionEvent actionEvent) {
    }

    public void btnImogeOnAction(ActionEvent actionEvent) {
    }

    public void txtMessageOnAction(ActionEvent actionEvent) {
    }
}
