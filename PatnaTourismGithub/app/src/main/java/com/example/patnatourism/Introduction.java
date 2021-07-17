package com.example.patnatourism;

public class Introduction {
    private final int imageResourceId;
    private final String name;
    private final String website;
    private String text;

    public Introduction(int imageResourceId, String text, String name, String website) {
        this.imageResourceId = imageResourceId;
        this.text = text;
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWebsite() {
        return website;
    }

}
