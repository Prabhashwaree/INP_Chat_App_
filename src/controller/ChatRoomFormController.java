package controller;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;


public class ChatRoomFormController extends Thread {


    public Label lblName;
    public VBox vBox;
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
        String userName=LoginFormController.userName;
        lblName.setText(String.valueOf(userName));
        try {
            socket = new Socket("localhost", 8000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
            System.out.println("mmmm");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();

                String[] sms = msg.split(" ");
                String cmd = sms[0];

                StringBuilder fullMassage = new StringBuilder();
                for (int i = 1; i < sms.length; i++) {
                    fullMassage.append(sms[i]);
                    System.out.println(fullMassage);
                }
                System.out.println(fullMassage);

                String[] massageArray = msg.split(" ");
                String m = "";
                for (int i = 0; i < massageArray.length - 1; i++) {
                    m += massageArray[i + 1] + " ";
                }
                System.out.println(massageArray);

                Text text = new Text(m);
                String firstChars = "";

                if (firstChars.equalsIgnoreCase("img")) {

                    if (!cmd.equalsIgnoreCase(lblName.getText())) {

                    }
                } else {
                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(lblName.getText() + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }
                    HBox hBox = new HBox(12); //12

                    if (!cmd.equalsIgnoreCase(lblName.getText() + ":")) {

                    } else {
                        tempFlow.getStyleClass().add("tempFlow");
                        Text text2=new Text(fullMassage+":Me");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                    }
                    hBox.getStyleClass().add("hbox");
                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send() {
        String msg = txtMessage.getText();
        System.out.println("msg   :"  + msg);
        writer.println(lblName.getText() + ": " + txtMessage.getText());
        txtMessage.clear();
    }




    public void btnCamaraOnAction(ActionEvent actionEvent) {
    }

    public void btnFileOnAction(ActionEvent actionEvent) throws IOException {
        send();
    }

    public void btnImogeOnAction(ActionEvent actionEvent) {
        send();
    }

    public void txtMessageOnAction(ActionEvent actionEvent) throws IOException {
        send();
    }
}
