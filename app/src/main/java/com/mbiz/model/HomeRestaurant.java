package com.mbiz.model;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by Aquad on 26-12-2017.
 */

public class HomeRestaurant {
    private String id;
    private String category_name;
    private String slug;
    private String image;
    private String icon;
    private String parent_id;
    private String parent;
    private String display_order;
    private String status;
    private List<CategoryChild> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CategoryChild> getChild() {
        return child;
    }

    public void setChild(List<CategoryChild> child) {
        this.child = child;
    }
}
