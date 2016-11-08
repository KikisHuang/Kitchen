package com.example.admin.bean;

import java.io.Serializable;

/**
 * Created by ${Kikis} on 2016-09-07.
 */

public class Foodbean implements Serializable {

    String FoodName;
    int FoodState;
    int FoodNumber;
    String SellPrice;
    String CSID;

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    String Detail;

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    String DateTime;

    public String getTypeIP() {
        return TypeIP;
    }

    public void setTypeIP(String typeIP) {
        TypeIP = typeIP;
    }

    String TypeIP;

    public int getFoodNumber() {
        return FoodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        FoodNumber = foodNumber;
    }

    public String getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(String sellPrice) {
        SellPrice = sellPrice;
    }

    public String getCSID() {
        return CSID;
    }

    public void setCSID(String CSID) {
        this.CSID = CSID;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public int getFoodState() {
        return FoodState;
    }
    public void setFoodState(int foodState) {
        FoodState = foodState;
    }


}
