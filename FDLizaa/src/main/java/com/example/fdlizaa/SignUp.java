package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class SignUp {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton Female;
    @FXML
    private CheckBox Iaccept;
    @FXML
    private Button SignUp;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label ErrorLabel;

    @FXML
    private void singUp(ActionEvent actionEvent) throws IOException, SQLException {
        String username1 = usernameField.getText().trim();
        String password1 = passwordField.getText().trim();
        String email1 = emailField.getText().trim();
        LocalDate birthDate = datePicker.getValue();

        String gender = "";
        if (male.isSelected()) {
            gender = "Male";
        } else if (Female.isSelected()) {
            gender = "Female";
        }

        boolean check = Iaccept.isSelected();

        if (username1.isEmpty() || password1.isEmpty() || email1.isEmpty() || gender.isEmpty() || birthDate == null || !check) {
            ErrorLabel.setText("Fill all fields!");
            return;
        }

        try (Connection connection = DriverManager.getConnection(
                HelloApplication.url, HelloApplication.username, HelloApplication.password)) {

            String sql = "insert into users (username, password, email, gender) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username1);
            preparedStatement.setString(2, password1);
            preparedStatement.setString(3, email1);
            preparedStatement.setString(4, gender);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                changeSceneToHome(actionEvent);
            } else {
                ErrorLabel.setText("Failed to create account.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void changeSceneToHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void BackToStart(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}
