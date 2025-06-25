package com.example.fdlizaa;

public class FavouriteItem {
    private final String name;
    private final String imagePath;

    public FavouriteItem(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}