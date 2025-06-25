package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class DunkinMenu {
    @FXML private Button backBTN;
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

    private final Map<ImageView, Double> priceMap = new HashMap<>();
    private final Map<ImageView, Integer> quantityMap = new HashMap<>();
    private final Map<ImageView, String> nameMap = new HashMap<>();
    private double totalPrice = 0.0;

    @FXML
    public void initialize() {
        priceMap.put(plusButton1, 12.90); nameMap.put(plusButton1, "Bagel with salmon");
        priceMap.put(plusButton2, 12.90); nameMap.put(plusButton2, "DD Burger");
        priceMap.put(plusButton3, 10.90); nameMap.put(plusButton3, "Chicken Wrap");
        priceMap.put(plusButton4, 10.90); nameMap.put(plusButton4, "Wrap Veggie");
        priceMap.put(plusButton5, 7.20);  nameMap.put(plusButton5, "Croissant with Ham and Cheese");
        priceMap.put(plusButton6, 7.20);  nameMap.put(plusButton6, "Croissant with Double Cheese");
        priceMap.put(plusButton7, 6.90);  nameMap.put(plusButton7, "Toasted Avocado");
        priceMap.put(plusButton8, 6.50);  nameMap.put(plusButton8, "Bagel with Melted Cheese");
        priceMap.put(plusButton9, 24.20); nameMap.put(plusButton9, "9 + 3 Donuts");
        priceMap.put(plusButton10, 13.70);nameMap.put(plusButton10, "5 + 1 Donuts");
        priceMap.put(plusButton11, 14.00);nameMap.put(plusButton11, "Hot Dog & Cold Americano");
        priceMap.put(plusButton12, 17.10);nameMap.put(plusButton12, "Croissant with Ham & Cheese (combo)");
        priceMap.put(plusButton13, 5.70); nameMap.put(plusButton13, "Cold Dunkin Original");
        priceMap.put(plusButton14, 8.20); nameMap.put(plusButton14, "Iced Coffee with Vanilla");
        priceMap.put(plusButton15, 10.90);nameMap.put(plusButton15, "Iced Coffee with Ice Cream");

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
    private void goToCart(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Cart.fxml"));
        Parent root = loader.load();

        CartController cartController = loader.getController();

        List<Cartitem> cartItems = new ArrayList<>();
        for (Map.Entry<ImageView, Integer> entry : quantityMap.entrySet()) {
            int quantity = entry.getValue();
            if (quantity > 0) {
                double price = priceMap.get(entry.getKey());
                String name = nameMap.get(entry.getKey());
                double total = quantity * price;
                cartItems.add(new Cartitem(name, quantity, total));
            }
        }

        cartController.fillCart(cartItems);

        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backtoHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}