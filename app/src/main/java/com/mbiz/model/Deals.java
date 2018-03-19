package com.mbiz.model;

/**
 * Created by Aquad on 26-12-2017.
 */

public class Deals {
    private int id;
    private String name;
    private String title; // title
    private String image1;// image1
    //private String image1 = "http://www.stubuz.com/mbiz/assets/uploads/american-food.jpg";

    public Deals(int id, String name, String title, String image1) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.image1 = image1;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }

    public String getTitle() { return title; }

    public String getImage1() { return "http://www.stubuz.com/mbiz/assets/"+image1; }
}
