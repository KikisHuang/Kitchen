package com.example.admin.asynck;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.admin.utils.HttpURlConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.example.admin.bean.Final.VERIFY_KEY;
import static com.example.admin.kitchen.AllDeskActivity.setData;

/**
 * Created by ${Kikis} on 2016-10-25.
 */
public class QueryDeskAsynctask extends AsyncTask {
    private Context context;
    private ProgressDialog pd;

    public QueryDeskAsynctask(Context context) {
        this.context = context;
    }

    public QueryDeskAsynctask() {
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);

        pd.setMessage("查询中...");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        String result = "";
        String type = String.valueOf(params[0]);

        String menukey = VERIFY_KEY;
        String url = String.valueOf(params[1]);

        StringBuilder sb = new StringBuilder(url);
        sb.append("?");
        try {
            sb.append("menukey=" + URLEncoder.encode(menukey.toString(), "UTF-8") + "&");

            sb.append("type=" + URLEncoder.encode(type.toString(), "UTF-8") + "&");



            result = HttpURlConnection.getHttpURlConnection(sb);

            if (result.isEmpty()) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Object o) {

        String result = String.valueOf(o);
        if (!result.isEmpty()) {

            try {
                List<String> list = new ArrayList<>();
                JSONArray array = new JSONArray(result);

            for(int i =0;i<array.length();i++){

                JSONObject object = array.getJSONObject(i);

                list.add(object.getString("DeskName"));
            }

                setData(context,list);
                pd.cancel();
            } catch (JSONException e) {
                e.printStackTrace();
                pd.cancel();
            }
        } else {

            Toast.makeText(context, "没有查询到桌号...", Toast.LENGTH_SHORT).show();
            pd.cancel();
            return;
        }
    }
}
