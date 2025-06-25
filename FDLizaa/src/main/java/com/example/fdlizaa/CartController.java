package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartController {
    @FXML
    private VBox cartItemsVBox;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private TextField promoField;
    @FXML
    private Label promoStatusLabel;

    private double totalPrice = 0.0;
    private boolean promoApplied = false;
    private List<Cartitem> currentItems;

    public void fillCart(List<Cartitem> cartItems) {
        this.currentItems = cartItems;
        cartItemsVBox.getChildren().clear();
        totalPrice = 0.0;

        for (Cartitem item : cartItems) {
            HBox row = new HBox(40);
            Label nameLabel = new Label(item.nameProperty().get());
            Label quantityLabel = new Label(String.valueOf(item.quantityProperty().get()));
            Label priceLabel = new Label(String.format("%.2f ₾", item.totalPriceProperty().get()));

            row.getChildren().addAll(nameLabel, quantityLabel, priceLabel);
            cartItemsVBox.getChildren().add(row);

            totalPrice += item.totalPriceProperty().get();
        }

        updateTotalLabel();
    }

    @FXML
    private void applyPromo(ActionEvent event) {
        String promoCode = promoField.getText().trim();

        if (promoApplied) {
            promoStatusLabel.setText("Promocode is used already");
            return;
        }

        if (promoCode.equalsIgnoreCase("DISCOUNT30")) {
            for (Cartitem item : currentItems) {
                double discounted = item.totalPriceProperty().get() * 0.70;
                item.totalPriceProperty().set(discounted);
            }

            totalPrice = 0.0;
            for (Cartitem item : currentItems) {
                totalPrice += item.totalPriceProperty().get();
            }

            promoStatusLabel.setText("-30% Discount is activated");
            promoApplied = true;

            fillCart(currentItems);
        } else {
            promoStatusLabel.setText("Invalid Promocode");
        }

        updateTotalLabel();
    }

    private void updateTotalLabel() {
        totalPriceLabel.setText(String.format("%.2f ₾", totalPrice));
    }

    @FXML
    private void backtoHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SendOrder(ActionEvent e) throws IOException {
        if (currentItems != null && !currentItems.isEmpty()) {
            OrderHistoryController.previousOrders.add(new ArrayList<>(currentItems));
        }

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/OrderSent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}