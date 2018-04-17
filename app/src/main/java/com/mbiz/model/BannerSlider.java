package com.mbiz.model;

/**
 * Created by Aquad on 03/04/2018.
 */

public class BannerSlider {
    String id;
    String name;
    String image;
    String status;
    String order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String child) {
        this.order= child;
    }
}
