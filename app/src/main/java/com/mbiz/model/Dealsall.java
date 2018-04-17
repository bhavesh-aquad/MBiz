package com.mbiz.model;

/**
 * Created by Aquad on 12/03/2018.
 */

public class Dealsall {

    private int id;
    private String mname;
    private String moffer;
    private int image;
    private int mapicon;

    public Dealsall(int id, String name, String offer, int image, int mapicon) {
        this.id = id;
        this.mname = name;
        this.moffer = offer;
        this.image = image;
        this.mapicon = mapicon;
    }

    public int getId() {
        return id;
    }

    public String getMname() {
        return mname;
    }

    public String getMoffer() {
        return moffer;
    }

    public int getImage() {
        return image;
    }

    public int getMapicon() { return mapicon; }
}
