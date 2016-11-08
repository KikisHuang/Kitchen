package com.example.admin.bean;

import java.io.Serializable;

/**
 * Created by ${Kikis} on 2016-08-31.
 */
public class MsgData implements Serializable{


    public String getCSID() {
        return CSID;
    }

    public void setCSID(String CSID) {
        this.CSID = CSID;
    }

    public String getDeskID() {
        return DeskID;
    }

    public void setDeskID(String deskID) {
        DeskID = deskID;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
    String Key;
    String CSID;
    String DeskID;
    String DateTime;
    String Message;

}
