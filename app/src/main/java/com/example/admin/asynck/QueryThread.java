package com.example.admin.asynck;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.admin.bean.MsgData;
import com.example.admin.myinterface.MessageAskResponse;
import com.example.admin.utils.HttpURlConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.example.admin.bean.Final.ONE_START_THERAD;
import static com.example.admin.bean.Final.THREADCONTROL;
import static com.example.admin.bean.Final.VERIFY_KEY;

/**
 * Created by ${Kikis} on 2016-09-01.
 */
public class QueryThread extends Thread {

    public static final String TAG = "QueryThread";

    private String typeID = "";
    private String URL = "";
    private String result = "";
    private List<MsgData> mlist;
    private MessageAskResponse asyncResponse;
    private Handler mHandler;

    public void setMessageResponse(MessageAskResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    public QueryThread(String typeID, String URL, Handler mHandler) {

        this.typeID = typeID;
        this.URL = URL;
        this.mHandler = mHandler;
    }


    @Override
    public void run() {

        while (THREADCONTROL) {

            try {
                if (!ONE_START_THERAD) {

                    Thread.sleep(10000);
                }
                Log.i(TAG, "查询线程启动了...");

                String menukey = VERIFY_KEY;

                StringBuilder sb = new StringBuilder(URL);

                sb.append("?");

                sb.append("menukey=" + URLEncoder.encode(menukey.toString(), "UTF-8") + "&");

                sb.append("TypeID=" + URLEncoder.encode(typeID.toString(), "UTF-8") + "&");

                result = HttpURlConnection.getHttpURlConnection(sb);

                JSONArray array = new JSONArray(result);

                mlist = new ArrayList<>();

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);

                    MsgData Msg = new MsgData();

                    Msg.setKey(object.getString("Key"));
                    Msg.setCSID(object.getString("CSID"));
                    Msg.setDeskID(object.getString("DeskID"));
                    Msg.setDateTime(object.getString("DateTime"));
                    Msg.setMessage(object.getString("Message"));

                    mlist.add(Msg);
                }

            } catch (Exception e) {
                Log.i(TAG, "Error!!  抛出异常信息 ===" + e);
            }


            if (result.equals("false")) {

                Log.i(TAG, "接收消息失败..");
            }

            if (!result.isEmpty() && !result.equals("false")) {

                Bundle b = new Bundle();

                Message msg = Message.obtain();
                b.putSerializable("mlist", (Serializable) mlist);
                msg.setData(b);
                mHandler.sendMessage(msg);
                Log.i(TAG, "接收消息成功..");

            }

        }

    }

}
