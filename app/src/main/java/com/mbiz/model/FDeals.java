package com.mbiz.model;

/**
 * Created by Aquad on 21/03/2018.
 */

public class FDeals {
    private int id;
    private String mname;
    //private String title; // title
    private int mthumbnail;// image1
    //private String image1;
    //private String image1 = "http://www.stubuz.com/mbiz/assets/uploads/american-food.jpg";

    public FDeals(int id, String name, int image) {
        this.id = id;
        this.mname = name;
        // this.title = title;
        this.mthumbnail = image;
        //this.image1 = image1;
    }

    public int getId() {return id;}

    public String getMname() {
        return mname;
    }

    public int getMthumbnail() {
        return mthumbnail;
    }

    // public String getImage1(){ return image1; }
}
