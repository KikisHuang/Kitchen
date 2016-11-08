package com.example.admin.asynck;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.bean.MsgData;
import com.example.admin.myinterface.MessageAskResponse;
import com.example.admin.utils.HttpURlConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.example.admin.bean.Final.VERIFY_KEY;

/**
 * Created by ${Kikis} on 2016-08-31.
 */

public class CommonAsynctask extends AsyncTask {

    private static final String TAG = "CommonAsynctask";
    private Context context;
    private List<String> list;
    private String result = "";
    private ProgressDialog pd;
    private MessageAskResponse asyncResponse;
    private List<MsgData> mlist;

    public void setMessageResponse(MessageAskResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    public CommonAsynctask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);

        pd.setMessage("正在查询消息...");
        pd.setCancelable(false);
        pd.show();

    }

    @Override
    protected Object doInBackground(Object[] params) {

        String menukey = VERIFY_KEY;

        String value = String.valueOf(params[0]);
        String URL = String.valueOf(params[1]);

        StringBuilder sb = new StringBuilder(URL);

        sb.append("?");

        try {
            sb.append( "menukey=" + URLEncoder.encode(menukey.toString(), "UTF-8") + "&");

            sb.append( "TypeID=" + URLEncoder.encode(value.toString(), "UTF-8") + "&");

            result = HttpURlConnection.getHttpURlConnection(sb);

            JSONArray array = new JSONArray(result);

            mlist = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);

                MsgData Msg = new MsgData();

                Msg.setCSID(object.getString("CSID"));
                Msg.setDeskID(object.getString("DeskID"));
                Msg.setDateTime(object.getString("DataTime"));
                Msg.setMessage(object.getString("Message"));

                mlist.add(Msg);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }


    @Override
    protected void onPostExecute(Object o) {

        try {
            String result = (String) o;

            Log.i(TAG, "onPostExecute 返回值打印 --- " + result);
            if (result.equals("false")) {

                Toast.makeText(context, "接收消息失败....", Toast.LENGTH_SHORT).show();

                Log.i(TAG, "接收消息失败..");

                asyncResponse.onMessageReceivedFailed();

                pd.cancel();
                return;
            }

            if (!result.isEmpty()&&!result.equals("false")) {

                asyncResponse.onMessageReceivedSuccess(mlist);
                Log.i(TAG, "接收消息成功..");
                pd.cancel();

                return;

            }

        } catch (Exception e) {

            Log.i(TAG, "Error -----  抛出异常信息 = " + e);
            asyncResponse.onMessageReceivedFailed();

            pd.cancel();

            return;
        }


    }
}
