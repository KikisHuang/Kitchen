package com.example.admin.bean;

/**
 * Created by ${Kikis} on 2016-09-06.
 */
public class Bill {

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

    public String getOrderDateTime() {
        return OrderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        OrderDateTime = orderDateTime;
    }

    public String getTableNumber() {
        return TableNumber;
    }

    public void setTableNumber(String tableNumber) {
        TableNumber = tableNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }



    String CSID;
    String TableNumber;
    String OrderDateTime;
    String FoodName;
    double price;
    int Number;

}
