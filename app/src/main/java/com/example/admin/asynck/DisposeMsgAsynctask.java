package com.example.admin.asynck;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.bean.Bill;
import com.example.admin.utils.HttpURlConnection;

import java.net.URLEncoder;
import java.util.List;

import static com.example.admin.bean.Final.VERIFY_KEY;
import static com.example.admin.kitchen.MainActivity.Succeed;

/**
 * Created by ${Kikis} on 2016-09-06.
 */

public class DisposeMsgAsynctask extends AsyncTask {
    private static final String TAG = "DisposeMsgAsynctask";
    private Context context;
    private ProgressDialog pd;
    private String result = "";
    private int position = 0;
    private List<Bill> blist;
    private String TypeIp="";
    private int Msg;
    public DisposeMsgAsynctask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);

        pd.setMessage("处理中...");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {

       Msg = (int) params[1];

        if(Msg==0){

            String menukey = VERIFY_KEY;
            String url = String.valueOf(params[0]);

            String CSID = String.valueOf(params[2]);
            String Key = String.valueOf(params[3]);
            position = (int) params[4];
            String Pay = String.valueOf(params[5]);

            StringBuilder sb = new StringBuilder(url);

            sb.append("?");

            try {

                sb.append("menukey=" + URLEncoder.encode(menukey.toString(), "UTF-8") + "&");
                sb.append("Msg=" + URLEncoder.encode(String.valueOf(Msg), "UTF-8") + "&");
                sb.append("CSID=" + URLEncoder.encode(CSID.toString(), "UTF-8") + "&");
                sb.append("Key=" + URLEncoder.encode(Key.toString(), "UTF-8") + "&");
                sb.append("Pay=" + URLEncoder.encode(Pay.toString(), "UTF-8") + "&");

                result = HttpURlConnection.getHttpURlConnection(sb);

                if(result.equals("false")||result.equals("true")){

                    return result;

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG,"异常信息打印 ===" + e);
            }


        }else{

            String menukey = VERIFY_KEY;
            String url = String.valueOf(params[0]);

            String CSID = String.valueOf(params[2]);
            String Key = String.valueOf(params[3]);
            position = (int) params[4];

            StringBuilder sb = new StringBuilder(url);

            sb.append("?");

            try {

                sb.append("menukey=" + URLEncoder.encode(menukey.toString(), "UTF-8") + "&");
                sb.append("Msg=" + URLEncoder.encode(String.valueOf(Msg), "UTF-8") + "&");
                sb.append("CSID=" + URLEncoder.encode(CSID.toString(), "UTF-8") + "&");
                sb.append("Key=" + URLEncoder.encode(Key.toString(), "UTF-8") + "&");

                result = HttpURlConnection.getHttpURlConnection(sb);

                if(result.equals("false")||result.equals("true")){

                    return result;

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG,"异常信息打印 ===" + e);
            }
        }

//            else{
//
//
//                JSONArray array = new JSONArray(result);
//
//                blist =  new ArrayList<>();
//
//
//                for(int i=0;i<array.length();i++){
//
//                    JSONObject object = array.getJSONObject(i);
//                    Bill bill = new Bill();
//                    if(!TypeIp.equals(object.getString("TypeIP"))){
//                        TypeIp  = object.getString("TypeIP");
//                    }
//
//                    bill.setCSID(object.getString("CSID"));
//                    bill.setFoodName(object.getString("FoodName"));
//                    bill.setNumber(Integer.parseInt(object.getString("Number")));
//                    bill.setOrderDateTime(object.getString("OrderDateTime"));
//                    bill.setPrice(Double.parseDouble(object.getString("Price")));
//                    bill.setTableNumber(object.getString("TableNumber"));
//
//                    blist.add(bill);
//                }
//
//                return result;
//
//            }


        return result;
    }

    @Override
    protected void onPostExecute(Object o) {




            String result = String.valueOf(o);

            if (result.equals("false")) {

                Log.i(TAG,"操作失败...result ==== "+result);
                Toast.makeText(context, "操作失败...", Toast.LENGTH_SHORT).show();

                pd.cancel();
                return;
            }
            if (result.equals("true")) {

                Log.i(TAG,"操作成功...result ==== "+result);

                if(position==400){

                    Toast.makeText(context, "操作成功...", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                    ((Activity) context).finish();
                    return;
                }else{
                    Succeed(position);
                    pd.cancel();
                    return;
                }
            }
//            if(!result.equals("false")&&!result.equals("true")){
//
//
//
////                    new InvoiceThread(blist,TypeIp).start();
//
//                Log.i(TAG,"获得的IP地址 ==== "+TypeIp);
//                Succeed(position);
//                pd.cancel();
//                return;
//            }

        try {

        } catch (Exception e) {

            Toast.makeText(context, "与服务连接时发生异常....", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "返回数据异常....打印异常结果为 --- " + e);

            pd.cancel();
            return;
        }
    }

}
