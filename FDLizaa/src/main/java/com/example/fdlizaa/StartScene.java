package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScene {

    @FXML
    private Button LogIn;

    @FXML
    private Button SignUp;

    @FXML
    private ImageView background;

    @FXML
    private void LogInbutton(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LogIn.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void SignUpButton(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
