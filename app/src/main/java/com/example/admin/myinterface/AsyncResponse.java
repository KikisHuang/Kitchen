package com.example.admin.myinterface;

import com.example.admin.bean.DeskType;

import java.util.List;

/**
 * Created by ${Kikis} on 2016-08-30.
 */

public interface AsyncResponse {

    void onDataReceivedSuccess(List<DeskType> listData, String version);
    void onDataReceivedSuccess(List<DeskType> listData);

    void onDataReceivedFailed();

}