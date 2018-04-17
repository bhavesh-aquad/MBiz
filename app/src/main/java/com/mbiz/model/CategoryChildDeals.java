package com.mbiz.model;

/**
 * Created by Aquad on 05/04/2018.
 */

public class CategoryChildDeals {
    String id;
    String merchant_id;
    String mname;
    String mtitle;
    String category_id;
    String enabled;
    String type;
    String discount;
    String percentagewhat;
    String thisforthat;
    String thisforthatwhat;
    String activewhen;
    String activewhens;
    String maxpartysize;
    String description;
    String conditions;
    String dealsperday;
    String cashback_deals;
    String special_offer;
    String dfolder;
    String dthumbnail;
    String active_date;
    String merchant_name;
    String mthumbnail;
    String mlatitude;
    String mlongitude;
    String category_name;

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

    public String getCategory_id(){return category_id;}

    public void setCategory_id(String category_id){this.category_id = category_id;}

    public String getType(){return type;}

    public void setType(String type){this.type = type;}

    public String getMname() {
        return mname;
    }

    public void setMname(String name) {
        this.mname = name;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String child) {
        this.mtitle = child;
    }

    public String getMthumbnail() {
        return mthumbnail;
    }

    public void setMthumbnail(String mthumbnail) {
        this.mthumbnail = mthumbnail;
    }

    public String getMlatitude() {
        return mlatitude;
    }

    public void setMlatitude(String mlatitude) {
        this.mlatitude = mlatitude;
    }

    public String getMlongitude() {
        return mlongitude;
    }

    public void setMlongitude(String mlongitude) {
        this.mlongitude = mlongitude;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id= merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConditions(){ return conditions; }

    public void setConditions(String conditions){ this.conditions= conditions;}
}
