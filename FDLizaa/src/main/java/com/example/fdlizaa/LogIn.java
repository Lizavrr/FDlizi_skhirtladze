package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LogIn {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private CheckBox iAccept;
    @FXML
    private Button LogIn;
    @FXML
    private ImageView background;
    @FXML
    private Button back;
    @FXML
    private Label error;

    @FXML
    private void backToStart(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PasswordTextFiled(ActionEvent e) throws SQLException, IOException {
        if (checkInfo()) {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } else {
            error.setText("wrong password or email!");
            error.setVisible(true);
        }
    }


    private boolean checkInfo() throws SQLException {
        Connection connection = DriverManager.getConnection(
                HelloApplication.url,
                HelloApplication.username,
                HelloApplication.password
        );

        String email1 = emailField.getText();
        String password1 = passwordField.getText();

        PreparedStatement statement = connection.prepareStatement(
                "select * from users where email = ? and password = ?"
        );
        statement.setString(1, email1);
        statement.setString(2, password1);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            HelloApplication.loggedInUserId = resultSet.getInt("id");

            emailField.clear();
            passwordField.clear();
            return true;
        } else {
            return false;
        }
    }
}

