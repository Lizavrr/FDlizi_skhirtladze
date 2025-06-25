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

public class McDonaldsMenu {
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
    @FXML private ImageView plusButton18;
    @FXML private ImageView plusButton19;

    private final Map<ImageView, Double> priceMap = new HashMap<>();
    private final Map<ImageView, Integer> quantityMap = new HashMap<>();
    private final Map<ImageView, String> nameMap = new HashMap<>();
    private double totalPrice = 0.0;

    @FXML
    public void initialize() {
        priceMap.put(plusButton1, 15.95); nameMap.put(plusButton1, "Big Mac");
        priceMap.put(plusButton2, 13.95); nameMap.put(plusButton2, "Triple Cheeseburger");
        priceMap.put(plusButton3, 13.85); nameMap.put(plusButton3, "Royal Cheeseburger");
        priceMap.put(plusButton4, 13.45); nameMap.put(plusButton4, "McCrispy");
        priceMap.put(plusButton5, 6.25);  nameMap.put(plusButton5, "Cheeseburger");
        priceMap.put(plusButton6, 6.00);  nameMap.put(plusButton6, "Hamburger");
        priceMap.put(plusButton7, 6.55);  nameMap.put(plusButton7, "Standard Fries");
        priceMap.put(plusButton8, 6.15);  nameMap.put(plusButton8, "Medium Fries");
        priceMap.put(plusButton9, 5.85);  nameMap.put(plusButton9, "Chicken McNuggets");
        priceMap.put(plusButton10, 5.85); nameMap.put(plusButton10, "Spicy Chicken McNuggets");
        priceMap.put(plusButton11, 4.45); nameMap.put(plusButton11, "Coca-Cola");
        priceMap.put(plusButton12, 4.45); nameMap.put(plusButton12, "Fanta");
        priceMap.put(plusButton13, 2.50); nameMap.put(plusButton13, "Water");
        priceMap.put(plusButton14, 5.30); nameMap.put(plusButton14, "Chocolate Ice Cream");
        priceMap.put(plusButton15, 5.30); nameMap.put(plusButton15, "Caramel Ice Cream");
        priceMap.put(plusButton16, 5.30); nameMap.put(plusButton16, "Strawberry Ice Cream");
        priceMap.put(plusButton17, 5.25); nameMap.put(plusButton17, "Chocolate Muffin");
        priceMap.put(plusButton18, 25.00); nameMap.put(plusButton18, "Chocolate Cake");
        priceMap.put(plusButton19, 7.45); nameMap.put(plusButton19, "Smarties McFlurry");

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Cart.fxml"));
        Parent root = loader.load();

        CartController cartController = loader.getController();

        List<Cartitem> cartItems = new ArrayList<>();
        for (Map.Entry<ImageView, Integer> entry : quantityMap.entrySet()) {
            int quantity = entry.getValue();
            if (quantity > 0) {
                double price = priceMap.get(entry.getKey());
                double total = quantity * price;
                String name = nameMap.get(entry.getKey());
                cartItems.add(new Cartitem(name, quantity, total));
            }
        }

        cartController.fillCart(cartItems);

        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}