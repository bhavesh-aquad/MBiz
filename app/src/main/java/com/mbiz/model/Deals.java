package com.mbiz.model;

/**
 * Created by Aquad on 26-12-2017.
 */

public class Deals {
    private int id;
    private String name;
    private String merchant_name; // title
    private String mthumbnail;// image1
    //private String image1 = "http://www.stubuz.com/mbiz/assets/uploads/american-food.jpg";
    private String mtagline;
    private String partner_distance;
    private String mlatitude;
    private String mlongitude;
    private String mflat;
    private String mbuilding;
    private String maddress1;
    private String maddress2;
    private String mtown;
    private String mcounty;
    private String mpostcode;
    private String category_name;
    private String address; //= "mflat" + "mbuilding" + "maddress1" + "maddress2" + "mtown" + "mcounty" + "mpostcode";

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getAddressAll(){

        if(!mflat.isEmpty()){address += mflat+", ";}
        else if(!mbuilding.isEmpty()){address += mbuilding +", ";}
        else if(!maddress1.isEmpty()){ address += maddress1 +", ";}
        else if(!maddress2.isEmpty()){ address += maddress2 +", ";}
        else if(!mtown.isEmpty()){address += mtown +"\n";}
        else if(!mcounty.isEmpty()){address += mcounty +", (";}
        else if(!mpostcode.isEmpty()){address += mpostcode +")";}
        return address;
    }
    public void setAddressAll(String address){this.address = address;}

    public Deals(int id, String name, String merchant_name, String mtagline, String partner_distance, String mthumbnail,
                String mpostcode, String mtown) {
        this.id = id;
        this.name = name;
        this.merchant_name = merchant_name;
        this.mthumbnail = mthumbnail;
        this.mtagline = mtagline;
        this.partner_distance = partner_distance;
        this.mflat = mflat;
        this.mbuilding = mbuilding;
        this.maddress1 = maddress1;
        this.maddress2 = maddress2;
        this.mtown = mtown;
        this.mcounty = mcounty;
        this.mpostcode = mpostcode;
        this.address = address;
    }
    public Deals() {

    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getMerchant_name() { return merchant_name; }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getMtagline(){ return mtagline;}

    public void setMtagline(String mtagline){this.mtagline = mtagline;}

    public String getPartner_distance(){return partner_distance;}

    public void setPartner_distance(String partner_distance){this.partner_distance = partner_distance;}

    public String getMthumbnail() { return mthumbnail; }

    public void setMthumbnail(String mthumbnail) {this.mthumbnail = mthumbnail;}

    public String getMlatitude(){ return mlatitude; }

    public void setMlatitude(String mlatitude){ this.mlatitude = mlatitude; }

    public String getMlongitude(){ return mlongitude; }

    public void setMlongitude(String mlongitude){ this.mlongitude = mlongitude; }

    public void setMflat(String mflat){ this.mflat = mflat; }

    public String getMflat(){ return mflat; }

    public void setMbuilding(String mbuilding){ this.mbuilding = mbuilding; }

    public String getMbuilding(){ return mbuilding;}

    public void setMaddress1(String maddress1){ this.maddress1 = maddress1; }

    public String getMaddress1(){ return maddress1; }

    public void setMaddress2(String maddress2){ this.maddress2 = maddress2; }

    public String getMaddress2(){ return maddress2; }

    public void setMtown(String mtown){ this.mtown = mtown; }

    public String getMtown(){ return mtown; }

    public void setMcounty(String mcounty){ this.mcounty = mcounty; }

    public String getMcounty(){ return mcounty; }

    public void setMpostcode(String mpostcode){ this.mpostcode = mpostcode;}

    public String getMpostcode(){ return mpostcode;}

    public void setAddress(String address){ this.address = address; }

    public String getAddress(){ return address;}

}
