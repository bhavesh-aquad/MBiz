package com.mbiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aquad on 15/04/2018.
 */

public class DealsDetails {
    String id;
    String merchant_id;
    String name;
    String title;
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
    String description; //": "[BULL] 4 weeks free in the kids golf school\n[BULL] 2 for 1 on 9 hole green fees[BULL] 20% off individual coaching",
    String conditions;  //": "7 days a week but not before 1pm on weekends and bank holidays",
    String dealsperday;
    String cashback_deals;
    String special_offer; //": "0",
    String dfolder; //": "2",
    String dthumbnail;  //": "",
    String dbanner;  //": "",
    String image1;  //": null,
    String image2; //": null,
    String image3; //": null,


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

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {this.conditions = conditions;}

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
