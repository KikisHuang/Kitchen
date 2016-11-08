package com.example.admin.myinterface;

import com.example.admin.bean.MsgData;

import java.util.List;

/**
 * Created by ${Kikis} on 2016-08-31.
 */

public interface MessageAskResponse {

    void onMessageReceivedSuccess(List<MsgData> mlist);

    void onMessageReceivedFailed();
}
