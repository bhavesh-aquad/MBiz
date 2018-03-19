package com.mbiz.model;

/**
 * Created by Aquad on 18/03/2018.
 */

public class Category {

    private int id;
    private String name;
    //private String title; // title
    private int image;// image1
    //private String image1;
    //private String image1 = "http://www.stubuz.com/mbiz/assets/uploads/american-food.jpg";

    public Category(int id, String name, int image) {
        this.id = id;
        this.name = name;
       // this.title = title;
        this.image = image;
        //this.image1 = image1;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

   // public String getImage1(){ return image1; }
}
