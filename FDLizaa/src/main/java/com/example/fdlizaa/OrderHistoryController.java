package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryController {
    @FXML
    private VBox orderHistoryBox;

    public static final List<List<Cartitem>> previousOrders = new ArrayList<>();

    @FXML
    public void initialize() {
        orderHistoryBox.getChildren().clear();

        if (previousOrders.isEmpty()) {
            Label noOrdersLabel = new Label("You have no past orders.");
            orderHistoryBox.getChildren().add(noOrdersLabel);
            return;
        }

        int orderNumber = 1;

        for (List<Cartitem> order : previousOrders) {
            VBox singleOrderBox = new VBox(5);
            singleOrderBox.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: #f9f9f9;");

            Label title = new Label("Order #" + orderNumber++);
            title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
            singleOrderBox.getChildren().add(title);

            double total = 0;
            boolean discountApplied = false;

            for (Cartitem item : order) {
                double unitPrice = item.totalPriceProperty().get() / item.quantityProperty().get();
                if (unitPrice % 1 != 0 && unitPrice % 0.05 != 0) {
                    discountApplied = true;
                }

                String itemLine = item.nameProperty().get() + " x" + item.quantityProperty().get() +
                        " = " + String.format("%.2f₾", item.totalPriceProperty().get());
                Label itemLabel = new Label(itemLine);
                singleOrderBox.getChildren().add(itemLabel);

                total += item.totalPriceProperty().get();
            }

            Label totalLabel = new Label("Total: " + String.format("%.2f₾", total));
            totalLabel.setStyle("-fx-font-weight: bold;");
            singleOrderBox.getChildren().add(totalLabel);

            if (discountApplied) {
                Label discountLabel = new Label("✔ Discount Applied");
                discountLabel.setStyle("-fx-text-fill: green; -fx-font-style: italic;");
                singleOrderBox.getChildren().add(discountLabel);
            }

            orderHistoryBox.getChildren().add(singleOrderBox);
        }
    }

    @FXML
    private void BackToHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}