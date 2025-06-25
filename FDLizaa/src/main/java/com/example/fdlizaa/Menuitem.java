package com.example.fdlizaa;

public class Menuitem {
    private final int id;
    private final String name;
    private final double price;

    public Menuitem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}