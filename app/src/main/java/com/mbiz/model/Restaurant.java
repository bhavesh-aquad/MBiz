package com.mbiz.model;

/**
 * Created by Aquad on 12/03/2018.
 */

public class Restaurant {
    private int id;
    private String name;
    private String offer;
//    private String price;
    private int image;
    private int mapicon;

    public Restaurant(int id, String name, String offer, int image, int mapicon) {
        this.id = id;
        this.name = name;
        this.offer = offer;
      //  this.price = price;
        this.image = image;
        this.mapicon = mapicon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOffer(){
        return offer;
    }

    //  public String getPrice() {
   //     return price;
   // }

    public int getImage() {
        return image;
    }

    public int getMapicon() {
        return mapicon;
    }
}

