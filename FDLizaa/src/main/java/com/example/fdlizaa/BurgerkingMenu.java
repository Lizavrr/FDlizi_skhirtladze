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

public class BurgerkingMenu {
    @FXML
    private Button backBTN;
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

    private final Map<ImageView, Double> priceMap = new HashMap<>();
    private final Map<ImageView, Integer> quantityMap = new HashMap<>();
    private final Map<ImageView, String> nameMap = new HashMap<>();
    private double totalPrice = 0.0;

    @FXML
    public void initialize() {
        priceMap.put(plusButton1, 17.00); nameMap.put(plusButton1, "Rodeo Whooper");
        priceMap.put(plusButton2, 15.75); nameMap.put(plusButton2, "Veggie Whooper");
        priceMap.put(plusButton3, 10.00); nameMap.put(plusButton3, "Hot Wrap");
        priceMap.put(plusButton4, 7.90);  nameMap.put(plusButton4, "Vegetarian Burger");
        priceMap.put(plusButton5, 6.30);  nameMap.put(plusButton5, "Kids Cheeseburger");
        priceMap.put(plusButton6, 5.80);  nameMap.put(plusButton6, "Children's Hamburger");
        priceMap.put(plusButton7, 11.00); nameMap.put(plusButton7, "Giant Box Potatoes");
        priceMap.put(plusButton8, 7.50);  nameMap.put(plusButton8, "Potatoes Bucket");
        priceMap.put(plusButton9, 9.00);  nameMap.put(plusButton9, "Cheese Sticks");
        priceMap.put(plusButton10, 6.00); nameMap.put(plusButton10, "Onion Rings");
        priceMap.put(plusButton11, 7.20); nameMap.put(plusButton11, "Chocolate Soufflé");
        priceMap.put(plusButton12, 2.50); nameMap.put(plusButton12, "Mineral Water");
        priceMap.put(plusButton13, 4.50); nameMap.put(plusButton13, "Fanta");
        priceMap.put(plusButton14, 4.50); nameMap.put(plusButton14, "Coca-Cola");

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

    @FXML
    private void backtoHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}