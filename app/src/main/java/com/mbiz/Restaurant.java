package com.mbiz;

/**
 * Created by Aquad on 26-12-2017.
 */

public class Restaurant {
    private int id;
    private String name;
    private String price;
    private int image;

    public Restaurant(int id, String name, String price, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
