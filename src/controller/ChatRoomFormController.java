package controller;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        client();
    }

    public void client(){
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
                String massage = reader.readLine();
                System.out.println("Line 63 :  " +massage);
                String[] massageTokens = massage.split(" ");
                System.out.println("Line 65 : "+massageTokens[0]);
                String c = massageTokens[0];
                System.out.println("Line 67 : " + c);
                StringBuilder FullMassage = new StringBuilder();
                System.out.println("Line 69 : " + FullMassage);
                for (int i = 1; i < massageTokens.length; i++) {
                    FullMassage.append(massageTokens[i]);
                }
                String[] array = massage.split(" ");
                System.out.println("Line 74 : " + array);
                String st = "";
                for (int i = 0; i < array.length - 1; i++) {
                    st += array[i + 1] + " ";
                    System.out.println("Line  78 : " + array[i]);
                }
                Text text = new Text(st);
                System.out.println("Line 81 : " + text);
                String firstLetter = "";
                System.out.println("Line  83 : " + firstLetter);
                if (st.length() > 3) {
                    firstLetter = st.substring(0, 3);
                }
                if (firstLetter.equalsIgnoreCase("img")) {
                    st = st.substring(3, st.length() - 1);
                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());
                    System.out.println("Line  91 : " + image);
                    ImageView imageView = new ImageView(image);
                    System.out.println("Line  93 : " + imageView);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);
                    HBox hBox = new HBox(10);
                    System.out.println("Line  98 : " + hBox);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);
                    if (!c.equalsIgnoreCase(lblName.getText())) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        Text text1 = new Text("  " + c + " :");
                        System.out.println("Line 107 : " + text1 + " : " +c);
                        hBox.getChildren().add(text1);
                        System.out.println("Line 109 : " + hBox.getChildren());
                        hBox.getChildren().add(imageView);
                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        System.out.println("Line 116 : " + text1);
                        hBox.getChildren().add(text1);

                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                } else {
                    TextFlow tempFlow = new TextFlow();
                    if (!c.equalsIgnoreCase(lblName.getText() + ":")) {
                        Text txtName = new Text(c + " ");
                        System.out.println("Line 130 : " + txtName + " : " + c);
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }
                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxHeight(400);
                    TextFlow flow = new TextFlow(tempFlow);
                    System.out.println("Line 139 : " + flow);
                    HBox hBox = new HBox(15);
                    System.out.println("Line  141 : " + hBox);
                    if (!c.equalsIgnoreCase(lblName.getText() + ":")) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);
                    } else {
                        Text text2 = new Text(FullMassage + ":Me");
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





    public void btnCamaraOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        if (filePath == null){
            new Alert(Alert.AlertType.WARNING, "Please Choose Your Photo !..").showAndWait();
        }else {
            writer.println(lblName.getText() + " " + "img" + filePath.getPath());
        }
    }

    public void btnFileOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void btnImogeOnAction(ActionEvent actionEvent) {

    }

    public void txtMessageOnAction(ActionEvent actionEvent) throws IOException {
       send();
    }
}
