package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public Button btnLogin;
    public AnchorPane loginPane;
    static String userName;
    public TextField txtUserName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        userName = txtUserName.getText();
        if(userName.equals("")){
            new Alert(Alert.AlertType.WARNING, "Waradiii !..").showAndWait();
        }else {
            Stage stage = (Stage) txtUserName.getScene().getWindow();
            stage.close();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ChatRoomForm.fxml"))));
            stage1.setResizable(false);
            stage1.setTitle(userName);
            stage1.centerOnScreen();
            stage1.show();
        }

//        URL resource = getClass().getResource("../view/ChatRoomForm.fxml");
//        Parent load = FXMLLoader.load(resource);
//        Stage window = (Stage) loginPane.getScene().getWindow();
//        window.setScene(new Scene(load));
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
    }
}
