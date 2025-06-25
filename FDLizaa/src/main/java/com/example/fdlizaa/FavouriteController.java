package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FavouriteController {

    @FXML
    private FlowPane favouriteBox;

    @FXML
    public void initialize() {
        favouriteBox.getChildren().clear();

        if (Home.favourites.isEmpty()) {
            Label noFavLabel = new Label("You have no favourite items.");
            noFavLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
            favouriteBox.getChildren().add(noFavLabel);
            return;
        }

        for (FavouriteItem item : Home.favourites) {
            VBox itemBox = new VBox(5);
            itemBox.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #fffaf0;");
            itemBox.setPrefWidth(180);

            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(item.getImagePath())));
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);

            Label nameLabel = new Label(item.getName());
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

            itemBox.getChildren().addAll(imageView, nameLabel);
            favouriteBox.getChildren().add(itemBox);
        }
    }

    @FXML
    private void mcdonaldsMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/McdonaldsMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void wendysMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WendysMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void kfcMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/kfcMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void subwayMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/subwayMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void burgerkingMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/burgerkingMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void dunkinMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dunkinMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DominosPizzaMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DominosPizzaMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void MacshaurmaMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MacshaurmaMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goBackHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}