package com.mbiz.application;

import com.mbiz.model.CategoryChild;
import com.mbiz.model.CategoryChildDeals;
import com.mbiz.model.Deals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aquad on 26/03/2018.
 */

public class SingleInstance {
    private static final SingleInstance ourInstance = new SingleInstance();

    public static SingleInstance getInstance() {
        return ourInstance;
    }

    private SingleInstance() {
    }


    private List<String> tabsList = new ArrayList<>();

    public List<String> getTabsList() {
        return tabsList;
    }

    public void setTabsList(List<String> tabsList) {
        this.tabsList = tabsList;
    }

    private List<CategoryChild> homeChild = new ArrayList<>();

    public List<CategoryChild> getHomeChild() {
        return homeChild;
    }

    public void setHomeChild(List<CategoryChild> homeChild) {
        this.homeChild = homeChild;
    }

    private List<Deals> dealsList = new ArrayList<>();

    public List<Deals> getDealsList(){ return dealsList; }

    public void setDealsList(List<Deals> dealsList){ this.dealsList = dealsList; }
}
