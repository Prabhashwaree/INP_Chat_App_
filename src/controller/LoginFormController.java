package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public Button btnLogin;
    public AnchorPane loginPane;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ChatRoomForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) loginPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
