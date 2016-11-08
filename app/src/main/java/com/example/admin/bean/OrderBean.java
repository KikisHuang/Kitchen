package com.example.admin.bean;

/**
 * Created by ${Kikis} on 2016-10-25.
 */
public class OrderBean {

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    String Time;
    String State;

    public String getCSID() {
        return CSID;
    }

    public void setCSID(String CSID) {
        this.CSID = CSID;
    }

    String CSID;

}
