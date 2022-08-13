package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import javax.swing.text.html.ImageView;
import java.awt.*;
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
    public VBox vBox;

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
            try {
                while (true) {








                        File file = new File(st)
//                        Image image = new Image(file.toURI().toString());
//
//                        ImageView imageView = new ImageView(image);
//
//                        imageView.setFitHeight(150);
//                        imageView.setFitWidth(200);


                        HBox hBox = new HBox(10);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);


                        

                        }

                        Platform.runLater(() -> vBox.getChildren().addAll(hBox));


                    } else {
                        //For the Text
//                    text.setFill(Color.WHITE);
                        //   text.getStyleClass().add("message");
                        TextFlow tempFlow = new TextFlow();

                        if (!cmd.equalsIgnoreCase(lblName.getText() + ":")) {
                            Text txtName = new Text(cmd + " ");
                            txtName.getStyleClass().add("txtName");
                            tempFlow.getChildren().add(txtName);
                        }

                        tempFlow.getChildren().add(text);
                        tempFlow.setMaxWidth(200); //200

                        TextFlow flow = new TextFlow(tempFlow);

                        HBox hBox = new HBox(12); //12

                        //=================================================


                        if (!cmd.equalsIgnoreCase(lblName.getText() + ":")) {

                            //  tempFlow.getStyleClass().add("tempFlowFlipped");
                            //  flow.getStyleClass().add("textFlowFlipped");
                            vBox.setAlignment(Pos.TOP_LEFT);
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.getChildren().add(flow);

                        } else {
                            // text.setFill(Color.WHITE);
                            // tempFlow.getStyleClass().add("tempFlow");
                            // flow.getStyleClass().add("textFlow");
                            Text text2=new Text(fullMsg+":Me");
                            TextFlow flow2 = new TextFlow(text2);
                            hBox.setAlignment(Pos.BOTTOM_RIGHT);
                            hBox.getChildren().add(flow2);
                        }
                        //  hBox.getStyleClass().add("hbox");
                        Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public void btnFileOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void btnImogeOnAction(ActionEvent actionEvent) {
    }

    public void txtMessageOnAction(ActionEvent actionEvent) throws IOException {
        send();
    }
}
