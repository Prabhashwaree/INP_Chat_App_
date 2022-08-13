package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    public Label lblName;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    private FileChooser fileChooser;
    private File filePath;

    public void initialize() {
        String userName=LoginFormController.userName;
        lblName.setText(String.valueOf(userName));
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

    @Override
    public void run() {
        while (true){
            
        }
    }

    public void send(){
        String msg = txtMessage.getText();
        System.out.println(txtMessage.getText());
        writer.println(lblName.getText() + ": " + txtMessage.getText());
        txtMessage.clear();
        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }


        public void txtAreaOnAction(MouseEvent mouseEvent) {
            send();
          }

    public void btnCamaraOnAction(ActionEvent actionEvent) {
    }

    public void btnFileOnAction(ActionEvent actionEvent) {
    }

    public void btnImogeOnAction(ActionEvent actionEvent) {
    }

    public void txtMessageOnAction(ActionEvent actionEvent) {
        send();
    }
}
