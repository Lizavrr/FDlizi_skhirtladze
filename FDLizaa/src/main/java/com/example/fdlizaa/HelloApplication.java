package com.example.fdlizaa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static String url = "jdbc:mysql://localhost:3306/fd";
    public static String username = "root";
    public static String password = "password";

    public static int loggedInUserId = -1;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("/fxml/StartScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}