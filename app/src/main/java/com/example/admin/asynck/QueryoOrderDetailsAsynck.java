package com.example.admin.asynck;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.admin.bean.OrderBean;
import com.example.admin.utils.HttpURlConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.example.admin.bean.Final.VERIFY_KEY;
import static com.example.admin.kitchen.OrderDetailsActivity.setData;

/**
 * Created by ${Kikis} on 2016-10-25.
 */
public class QueryoOrderDetailsAsynck extends QueryDeskAsynctask {
    private Context context;
    private ProgressDialog pd;
    String deskname="";

    public QueryoOrderDetailsAsynck(Context context) {
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
        String url = String.valueOf(params[1]);
         deskname = String.valueOf(params[0]);

        StringBuilder sb = new StringBuilder(url);
        sb.append("?");

        try {
            sb.append("menukey=" + URLEncoder.encode(menukey.toString(), "UTF-8") + "&");
            sb.append("DeskName=" + URLEncoder.encode(String.valueOf(deskname), "UTF-8") + "&");


            String result = HttpURlConnection.getHttpURlConnection(sb);

            return result;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        List<OrderBean> list;
        try {
        String result = String.valueOf(o);



            if (!result.equals("false")) {
                JSONArray array = new JSONArray(result);
                list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    OrderBean ob = new OrderBean();

                    ob.setTime(object.getString("Time"));
                    ob.setState(object.getString("OrderState"));
                    ob.setCSID(object.getString("CSID"));

                    list.add(ob);
                }

                setData(context,list,deskname);
                pd.cancel();
            } if(result.equals("false")) {

                Toast.makeText(context, "没有查询到订单...", Toast.LENGTH_SHORT).show();

                pd.cancel();
                return;
            }

        } catch (Exception e) {
            Toast.makeText(context, "与服务器连接异常...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            pd.cancel();
        }
    }
}
