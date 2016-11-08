package com.example.admin.kitchen;

import android.app.Activity;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.asynck.QuerydeskTypeAsynck;
import com.example.admin.bean.DeskType;
import com.example.admin.myinterface.AsyncResponse;
import com.example.admin.utils.CommonUtil;
import com.example.admin.utils.MyIntent;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.admin.bean.Final.END;
import static com.example.admin.bean.Final.START;
import static com.example.admin.utils.AlertDialogUtils.getVersionDialog;
import static com.example.admin.utils.CommonUtil.getVersion;
import static com.example.admin.utils.SharedPreference.setSharedPreference;

/**
 * Created by ${Kikis} on 2016-08-30.
 */

public class Welcom_Activity extends Activity {
    private RelativeLayout img;
    private Animation rotate;
    private Animation rotate2;
    private static Spinner spinner;
    private Button login_bt;
    private static ArrayAdapter<String> arr_adapter;
    private static String value = "";


    //    private List<DeskType> data_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        init();
        gainType();
//        ShowAnima(START);
        Login();
    }

    private void Login() {

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!value.isEmpty()) {

//                    ShowAnima(END);
                    //将TypeID存储;
                    setSharedPreference(Welcom_Activity.this,"TypeID",value);

                    MyIntent.setIntent(Welcom_Activity.this,"TypeID", value);

                    return;
                } else {

                    Toast.makeText(Welcom_Activity.this, "没有接收到桌号分类数据...", Toast.LENGTH_SHORT).show();

                    Log.i(TAG, "没有接收到桌号分类数据...");

                    return;

                }
            }
        });

    }

    private void gainType() {

        NetworkInfo info = CommonUtil.getNetworkInfo(Welcom_Activity.this);

        if (info != null) {

            QuerydeskTypeAsynck QTA = (QuerydeskTypeAsynck) new QuerydeskTypeAsynck(Welcom_Activity.this).execute();

            QTA.setOnAsyncResponse(new AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(List<DeskType> listData, String version) {

                    Log.d(TAG, "接口方法  onDataReceivedSuccess 成功获得数据");

                    if(version.equals(getVersion(Welcom_Activity.this))){

                        MySpinner(listData,Welcom_Activity.this);
                        return;

                    }else{

                        getVersionDialog(listData,Welcom_Activity.this,version);

                    }

                }

                @Override
                public void onDataReceivedSuccess(List<DeskType> listData) {

                    Log.d(TAG, "接口方法  onDataReceivedSuccess 成功获得数据");

                    MySpinner(listData,Welcom_Activity.this);
                    return;

                }

                @Override
                public void onDataReceivedFailed() {

                    Log.d(TAG, "接口方法  onDataReceivedFailed 获得数据失败");

                    return;
                }
            });

        } else {

            Toast.makeText(Welcom_Activity.this, "没有网络连接...", Toast.LENGTH_SHORT).show();

            Log.i(TAG, "没有网络连接...");

            return;
        }

    }


    public static void MySpinner(final List<DeskType> data_list, final Context context) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < data_list.size(); i++) {

            list.add(data_list.get(i).getTypeName());

            Log.i(TAG, "循环数据输出测试 ----- " + list.get(i));

        }

        //适配器
        arr_adapter = new ArrayAdapter<String>(context, R.layout.myspinner, list);

        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.drop_down_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                value = data_list.get(position).getTypeID();

                Log.i(TAG, "Spinner === position == " + position);

                Log.i(TAG, "Spinner === value == " + value);

                Log.i(TAG, "Spinner === id == " + id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


                Toast.makeText(context, "没有网络连接...", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "没有网络连接...");

            }
        });

    }

    private void ShowAnima(int id) {

        if(id == START){

            img.startAnimation(rotate);
            /**
             * 动画状态监听
             */
        }

        if(id == END){

            img.startAnimation(rotate2);

            rotate2.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {


                }

                @Override
                public void onAnimationRepeat(Animation animation) {


                }
            });

        }

    }

    private void init() {

        rotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);    //获取“旋转”动画资源
        rotate2 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate2);    //获取“旋转”动画资源

        spinner = (Spinner) findViewById(R.id.spinner);
        img = (RelativeLayout) findViewById(R.id.welcom_img);
        login_bt = (Button) findViewById(R.id.login_bt);

    }

}
