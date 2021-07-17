package com.example.patnatourism;

public class Category {
    private final String category;
    private int ImageResourceId;

    public Category(int imageResourceId, String name) {
        ImageResourceId = imageResourceId;
        this.category = name;
    }

    public int getImageResourceId() {
        return ImageResourceId;
    }

    public String getCategory() {
        return category;
    }
}
