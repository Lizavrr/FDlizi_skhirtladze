package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class SubwayMenu {
    @FXML private Label messageLabel;

    @FXML private ImageView plusButton1;
    @FXML private ImageView plusButton2;
    @FXML private ImageView plusButton3;
    @FXML private ImageView plusButton4;
    @FXML private ImageView plusButton5;
    @FXML private ImageView plusButton6;
    @FXML private ImageView plusButton7;
    @FXML private ImageView plusButton8;
    @FXML private ImageView plusButton9;
    @FXML private ImageView plusButton10;
    @FXML private ImageView plusButton11;
    @FXML private ImageView plusButton12;
    @FXML private ImageView plusButton13;
    @FXML private ImageView plusButton14;
    @FXML private ImageView plusButton15;
    @FXML private ImageView plusButton16;
    @FXML private ImageView plusButton17;

    private Map<ImageView, Double> priceMap = new HashMap<>();
    private Map<ImageView, Integer> quantityMap = new HashMap<>();
    private double totalPrice = 0.0;

    @FXML
    public void initialize() {
        priceMap.put(plusButton1, 20.00);
        priceMap.put(plusButton2, 20.00);
        priceMap.put(plusButton3, 19.00);
        priceMap.put(plusButton4, 19.00);
        priceMap.put(plusButton5, 19.00);
        priceMap.put(plusButton6, 18.00);
        priceMap.put(plusButton7, 12.50);
        priceMap.put(plusButton8, 12.50);
        priceMap.put(plusButton9, 4.95);
        priceMap.put(plusButton10, 3.95);
        priceMap.put(plusButton11, 1.50);
        priceMap.put(plusButton12, 1.50);
        priceMap.put(plusButton13, 1.50);
        priceMap.put(plusButton14, 1.50);
        priceMap.put(plusButton15, 3.90);
        priceMap.put(plusButton16, 3.95);
        priceMap.put(plusButton17, 3.95);

        for (ImageView button : priceMap.keySet()) {
            quantityMap.put(button, 0);
            button.setOnMouseClicked(e -> handlePlusClick(button));
        }
    }

    private void handlePlusClick(ImageView imageView) {
        int quantity = quantityMap.getOrDefault(imageView, 0);
        if (quantity < 5) {
            quantity++;
            quantityMap.put(imageView, quantity);
            String imagePath = "/quantity" + quantity + ".PNG";
            imageView.setImage(new Image(getClass().getResourceAsStream(imagePath)));

            recalculateTotal();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notice");
            alert.setHeaderText(null);
            alert.setContentText("You can order max 5 items");
            alert.showAndWait();
        }
    }

    private void recalculateTotal() {
        totalPrice = 0.0;
        for (Map.Entry<ImageView, Double> entry : priceMap.entrySet()) {
            int quantity = quantityMap.get(entry.getKey());
            totalPrice += quantity * entry.getValue();
        }
        System.out.println("Total price: " + totalPrice + "₾");
        messageLabel.setText("Total price : " + totalPrice + "₾");
    }

    @FXML
    private void backtoHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToCart(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Cart.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}