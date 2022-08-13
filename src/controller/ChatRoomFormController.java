package controller;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
                    System.out.println("Line 77 ->:"+massageArray[0]);
                }
                System.out.println(massageArray);

                Text text = new Text(m);
                System.out.println("Line 82  ->:"+text);
                String firstChars = "";

                if (m.length() > 3) {
                    firstChars = m.substring(0, 3);
                    System.out.println("Line 87  ->:"+firstChars);
                }


                if (firstChars.equalsIgnoreCase("img")) {


//                    m = m.substring(3, m.length() - 1);
//                    System.out.println("Line 95  :" + m);
//
//
//
//                    HBox hBox = new HBox(10);
//                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(lblName.getText())) {
//
//                        vBox.setAlignment(Pos.TOP_LEFT);
//                        hBox.setAlignment(Pos.CENTER_LEFT);
//
//                        Text text1 = new Text("  " + cmd + " :");
//                        System.out.println("Line 110 >:" +cmd);
//                        hBox.getChildren().add(text1);
////                        hBox.getChildren().add(imageView);

                    } else {
//                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
////                        hBox.getChildren().add(imageView);
//                        Text text1 = new Text(": Me ");
//                        hBox.getChildren().add(text1);

                    }
//                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));


                } else {
                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(lblName.getText() + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200);
                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12);

                    if (!cmd.equalsIgnoreCase(lblName.getText() + " :")) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);

                    } else {
                        Text text2 = new Text(fullMassage + " :Me");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                    }
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
