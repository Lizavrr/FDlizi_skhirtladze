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

public class WendysMenu {
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

    private final Map<ImageView, Double> priceMap = new HashMap<>();
    private final Map<ImageView, Integer> quantityMap = new HashMap<>();
    private final Map<ImageView, String> nameMap = new HashMap<>();
    private double totalPrice = 0.0;

    @FXML
    public void initialize() {
        priceMap.put(plusButton1, 15.95); nameMap.put(plusButton1, "Baconator");
        priceMap.put(plusButton2, 14.45); nameMap.put(plusButton2, "Portabella Melt");
        priceMap.put(plusButton3, 13.95); nameMap.put(plusButton3, "Triple Cheeseburger");
        priceMap.put(plusButton4, 13.25); nameMap.put(plusButton4, "Wendy's Burger");
        priceMap.put(plusButton5, 5.95);  nameMap.put(plusButton5, "Hamburger");
        priceMap.put(plusButton6, 6.25);  nameMap.put(plusButton6, "Cheeseburger");
        priceMap.put(plusButton7, 9.95);  nameMap.put(plusButton7, "BBQ Fries");
        priceMap.put(plusButton8, 9.95);  nameMap.put(plusButton8, "Mozzarella Sticks");
        priceMap.put(plusButton9, 6.55);  nameMap.put(plusButton9, "Curly Fries");
        priceMap.put(plusButton10, 5.95); nameMap.put(plusButton10, "Classic Fries");
        priceMap.put(plusButton11, 6.75); nameMap.put(plusButton11, "Frost Moth");
        priceMap.put(plusButton12, 4.65); nameMap.put(plusButton12, "Caramel Frost");
        priceMap.put(plusButton13, 4.65); nameMap.put(plusButton13, "Chocolate Frost");
        priceMap.put(plusButton14, 6.45); nameMap.put(plusButton14, "Shake Standard");
        priceMap.put(plusButton15, 1.95); nameMap.put(plusButton15, "Mtis Water");
        priceMap.put(plusButton16, 3.95); nameMap.put(plusButton16, "Coca-Cola");
        priceMap.put(plusButton17, 4.95); nameMap.put(plusButton17, "Sprite Classic");

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