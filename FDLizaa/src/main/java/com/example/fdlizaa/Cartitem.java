package com.example.fdlizaa;

import javafx.beans.property.*;

public class Cartitem {
    private final StringProperty name;
    private final IntegerProperty quantity;
    private final DoubleProperty totalPrice;

    public Cartitem(String name, int quantity, double totalPrice) {
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.totalPrice = new SimpleDoubleProperty(totalPrice);
    }

    public StringProperty nameProperty() { return name; }
    public IntegerProperty quantityProperty() { return quantity; }
    public DoubleProperty totalPriceProperty() { return totalPrice; }
}