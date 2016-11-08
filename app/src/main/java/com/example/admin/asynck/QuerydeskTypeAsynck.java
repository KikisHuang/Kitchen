package com.example.admin.asynck;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.bean.DeskType;
import com.example.admin.myinterface.AsyncResponse;
import com.example.admin.utils.HttpURlConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.example.admin.bean.Final.DESK_TYPE;
import static com.example.admin.bean.Final.HTTP;
import static com.example.admin.bean.Final.VERIFY_KEY;
import static com.example.admin.bean.Final.VERSION_QUERY;

/**
 * Created by ${Kikis} on 2016-08-30.
 */
public class QuerydeskTypeAsynck extends AsyncTask{
    private static final String TAG = "QuerydeskTypeAsynck";
    private String result = "";
    private Activity context;
    private List<DeskType> data_list;
    private AsyncResponse asyncResponse;
    private String Version = "";

    public void setOnAsyncResponse(AsyncResponse asyncResponse)
    {
        this.asyncResponse = asyncResponse;
    }
    public QuerydeskTypeAsynck(Activity context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        String menukey = VERIFY_KEY;

        StringBuilder sb = new StringBuilder(HTTP + DESK_TYPE);
        StringBuilder sb1 = new StringBuilder(HTTP + VERSION_QUERY);

        sb.append("?");
        sb1.append("?");

        try {
            sb.append("menukey=" + URLEncoder.encode(menukey.toString(), "UTF-8") + "&");
            sb1.append("menukey=" + URLEncoder.encode("0x204778502", "UTF-8") + "&");

            result = HttpURlConnection.getHttpURlConnection(sb);

            Version  = HttpURlConnection.getHttpURlConnection(sb1);

            JSONArray array = new JSONArray(result);

            data_list = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);

                DeskType DT = new DeskType();

                DT.setSystemID(object.getString("SystemID"));
                DT.setTypeID(object.getString("TypeID"));
                DT.setTypeName(object.getString("TypeName"));
                DT.setTypeStatus(object.getString("TypeStatus"));

                data_list.add(DT);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return data_list;
    }

    @Override
    protected void onPostExecute(Object o) {

        try {

            List<DeskType> data_list = (List<DeskType>) o;

            for(int i=0;i<data_list.size();i++){

                Log.i(TAG,"11111111111111111"+data_list.get(i).getTypeName());
            }

            Log.i(TAG, "result ---- " + result);

            if (result.equals("false")) {

                Toast.makeText(context, "key数据有误....", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "result ---- " + result);
                asyncResponse.onDataReceivedFailed();

                return;
            }

            if (!result.equals("")) {

                if(!Version.isEmpty()&&!Version.equals("NO_File")){

                    asyncResponse.onDataReceivedSuccess(data_list,Version);

                }else{

                    asyncResponse.onDataReceivedSuccess(data_list);
                }


                return ;
            }

        } catch (Exception e) {

            Toast.makeText(context, "无法与服务器端获得连接,请联系服务员", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "无法与服务器端获得连接 打印错误信息-----------------" + e);
			asyncResponse.onDataReceivedFailed();
            return ;
        }
    }


}
