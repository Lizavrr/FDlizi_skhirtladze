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

public class DominosPizzaMenu {
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

    private final Map<ImageView, Double> priceMap = new HashMap<>();
    private final Map<ImageView, String> nameMap = new HashMap<>();
    private final Map<ImageView, Integer> quantityMap = new HashMap<>();
    private double totalPrice = 0.0;

    @FXML
    public void initialize() {
        priceMap.put(plusButton1, 22.33); nameMap.put(plusButton1, "Double Pepperoni (Medium)");
        priceMap.put(plusButton2, 22.33); nameMap.put(plusButton2, "4 Medium Cheese");
        priceMap.put(plusButton3, 22.33); nameMap.put(plusButton3, "Bacon & Chicken Ranch (Medium)");
        priceMap.put(plusButton4, 22.33); nameMap.put(plusButton4, "Domino's Pizza (Medium)");
        priceMap.put(plusButton5, 21.63); nameMap.put(plusButton5, "Extravaganza (Medium)");
        priceMap.put(plusButton6, 21.63); nameMap.put(plusButton6, "4 Meat (Medium)");
        priceMap.put(plusButton7, 21.63); nameMap.put(plusButton7, "Mix (Medium)");
        priceMap.put(plusButton8, 21.63); nameMap.put(plusButton8, "Veggie (Medium)");
        priceMap.put(plusButton9, 21.63); nameMap.put(plusButton9, "Grilled Chicken Meat (Medium)");
        priceMap.put(plusButton10, 21.63); nameMap.put(plusButton10, "American Average");
        priceMap.put(plusButton11, 8.90); nameMap.put(plusButton11, "Lava Cake");
        priceMap.put(plusButton12, 1.35); nameMap.put(plusButton12, "Water");
        priceMap.put(plusButton13, 3.50); nameMap.put(plusButton13, "Sprite");
        priceMap.put(plusButton14, 3.50); nameMap.put(plusButton14, "Coca-Cola");

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
                cartItems.add(new Cartitem(name, quantity, quantity * price));
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