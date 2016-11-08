package com.example.admin.asynck;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.bean.Foodbean;
import com.example.admin.utils.HttpURlConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.admin.bean.Final.VERIFY_KEY;
import static com.example.admin.kitchen.QueryFoodActivity.setData;
import static com.example.admin.utils.AlertDialogUtils.getOnlyDialog;
import static com.example.admin.utils.SharedPreference.getSahrePreference;

/**
 * Created by ${Kikis} on 2016-09-07.
 */
public class QueryFoodAsynck extends AsyncTask {
    private ProgressDialog pd;
    private Activity context;
    private String result = "";
    private List<Foodbean> fblist;
    private  String num;

    public QueryFoodAsynck(Activity context) {
        this.context = context;
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

        String menukey = VERIFY_KEY;


        String url = String.valueOf(params[0]);
        num = String.valueOf(params[1]);
        String CSID = String.valueOf(params[2]);
        String TypeId = getSahrePreference(context,"TypeID");

        StringBuilder sb = new StringBuilder(url);
        sb.append("?");

        try {

            sb.append("menukey=" + URLEncoder.encode(menukey.toString().trim(), "UTF-8") + "&");
            sb.append("typeID=" + URLEncoder.encode(TypeId.toString().trim(), "UTF-8") + "&");
            sb.append("CSID=" + URLEncoder.encode(CSID.toString().trim(), "UTF-8") + "&");


            result = HttpURlConnection.getHttpURlConnection(sb);

            if (result.length()<=0 || result.equals("false")) {


                return result;

            } else {

                JSONArray array = new JSONArray(result);
                fblist = new ArrayList<>();

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Foodbean fb = new Foodbean();

                    fb.setTypeIP(object.getString("TypeIP").trim());
                    fb.setDateTime(object.getString("DateTime"));
                    fb.setFoodName(object.getString("FoodName").trim());
                    fb.setFoodState(Integer.parseInt(object.getString("FoodState").trim()));
                    fb.setFoodNumber(Integer.parseInt(object.getString("FoodNumber")));
                    fb.setSellPrice(object.getString("SellPrice"));
                    fb.setCSID(object.getString("CSID"));
                    fb.setDetail(object.getString("DetailName"));

                    fblist.add(fb);

                }
                return fblist;
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
            String result = String.valueOf(o);

            if (result.equals("false")) {

                Log.i(TAG,"result为false");
                getOnlyDialog(context, "没有查询到此桌号的订单");
                pd.cancel();
                return;
            }
            if (!result.isEmpty() && !result.equals("fasle") && result.length() > 0) {

                Log.i(TAG,"成功接收json");
                setData(context,fblist);
                pd.cancel();

                return;
            }

            pd.cancel();
            return;

        } catch (Exception e) {

            Log.i(TAG, "抛出异常信息..." + e);
            pd.cancel();
            return;
        }


    }
}
