package com.example.admin.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.asynck.DisposeMsgAsynctask;
import com.example.admin.asynck.QueryFoodAsynck;
import com.example.admin.asynck.UpdateVersionAsyncTask;
import com.example.admin.bean.DeskType;
import com.example.admin.kitchen.R;

import java.util.List;

import static com.example.admin.bean.Final.DISPOSE_MSG;
import static com.example.admin.bean.Final.HTTP;
import static com.example.admin.bean.Final.QUERY_FOOD;
import static com.example.admin.kitchen.Welcom_Activity.MySpinner;
import static com.example.admin.utils.CommonUtil.getWindowsManager;

/**
 * Created by ${Kikis} on 2016-09-06.
 */

public class AlertDialogUtils {


    private static final String TAG = "AlertDialogUtils";


    public static void getCommonDialog(final Context context, final String Pay, final String csid, final String key, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pay_verify, null);
        Button confirm  = (Button) view.findViewById(R.id.confirm);
        Button cancel = (Button) view.findViewById(R.id.cancel);
        TextView titleView = (TextView) view.findViewById(R.id.titleView);
        titleView.setText("选择的结账方式为 :"+ Pay);
//        final AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(view);

        int width = (int) (getWindowsManager("width",context) / 1.3);
        int height = (int) (getWindowsManager("height",context) /3.5);
        mAlertDialog.getWindow().setLayout(width, height);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DisposeMsgAsynctask(context).execute(HTTP+DISPOSE_MSG,0, csid, key,position,Pay);
                mAlertDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                return;
            }
        });


//        builder.setMessage("结账方式为:"+ Pay);
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog,
//                                int which) {
//
//                new DisposeMsgAsynctask(context).execute(HTTP+DISPOSE_MSG,0, csid, key,position,Pay);
//
//            }
//        });
//
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                return;
//            }
//        });
//        //创建并显示出来
//        builder.create().show();
    }


    public static void getOnlyDialog(final Context context, String Msg) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);


        builder.setMessage(Msg);

        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                return;
            }
        });
        //创建并显示出来
        builder.create().show();
    }

    public static void getEditextDialog(final Activity context) {


        LayoutInflater factory = LayoutInflater.from(context);//提示框
        final View view = factory.inflate(R.layout.edittext_layout, null);//这里必须是final的
        final EditText edit = (EditText) view.findViewById(R.id.editText);//获得输入框对象

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("输入需要查询的桌号");//提示框标题
        builder.setView(view);
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        if(edit.getText().toString().isEmpty()){

                            Toast.makeText(context,"没有桌号无法查询...",Toast.LENGTH_SHORT).show();
                            return;
                        }else{


                            new QueryFoodAsynck(context).execute(edit.getText().toString(),HTTP+QUERY_FOOD);

                            Log.i(TAG,"查询的edit数据为"+edit.getText().toString());

                            return;
                        }
                    }
                });


        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                return;
            }
        });

        builder.create().show();
    }

    public static void getPaymentMethodDialog(final Context context, final String csid, final String key, final int position1){

        /**
         * 自定义AlertDialog样式;
         */
        final String[] pay = {"微信", "支付宝", "现金", "刷卡", "其他"};

        ListAdapter mAdapter = new ArrayAdapter(context, R.layout.pay_dialog_item, pay);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pay_dialog, null);

        TextView titleView = (TextView)view.findViewById(R.id.titleView);
        ImageView titleImg = (ImageView) view.findViewById(R.id.titleImg);

        String title = "选择付款方式";
        titleView.setText(title);
        titleImg.setBackgroundResource(R.drawable.pay_img);

        ListView listview = (ListView)view.findViewById(android.R.id.list);
        listview.setAdapter(mAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(view);

        int width = (int) (getWindowsManager("width",context) / 1.3);
        int height = (int) (getWindowsManager("height",context) /1.49);
        mAlertDialog.getWindow().setLayout(width, height);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                Toast.makeText(context," Pay = "+ pay[position],Toast.LENGTH_SHORT).show();

                Log.i(TAG," 付款方式选择 === "+ pay[position]);
                getCommonDialog(context,pay[position],csid,key,position1);
                mAlertDialog.dismiss();
            }
        });



//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//        builder.setIcon(R.drawable.pay_img);
//        builder.setTitle("选择付款方式");
//        //    指定下拉列表的显示数据
//
//        //    设置一个下拉的列表选择项
//        builder.setItems(pay, new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
////                Toast.makeText(context, "选择为：" + cities[which], Toast.LENGTH_SHORT).show();
//
//                getCommonDialog(context,pay[which],csid,key,position);
//            }
//        });
//        builder.show();



    }

    public static void getVersionDialog(final List<DeskType> listData, final Activity context, final String version){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);


        builder.setMessage("查询到有新版本,请问是否更新?");
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which) {

                new UpdateVersionAsyncTask(context).execute(version);

                return;

            }
        });

        builder.setNegativeButton("跳过", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                MySpinner(listData,context);
                return;
            }
        });
        //创建并显示出来
        builder.create().show();
    }


}
