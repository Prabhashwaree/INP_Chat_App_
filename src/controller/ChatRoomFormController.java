package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
             reader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
             writer = new PrintWriter(socket.getOutputStream(),true);
             this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {


                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];

//                txtTextArea.appendText(cmd+"\n");
                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }


                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }
//======================================================================



                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }


                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);



                    File file = new File(st);
//                    Image image = new Image(file.toURI().toString());
//
//                    ImageView imageView = new ImageView(image);
//
//                    imageView.setFitHeight(150);
//                    imageView.setFitWidth(200);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(lblName.getText())) {

                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1=new Text("  "+cmd+" :");
                        hBox.getChildren().add(text1);
//                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
//                        hBox.getChildren().add(imageView);
                        Text text1=new Text(": Me ");
                        hBox.getChildren().add(text1);

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

    public void send(){
        String msg = txtMessage.getText();
        writer.println(lblName.getText() + ": " + txtMessage.getText());

        txtMessage.clear();

        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);

        }
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
