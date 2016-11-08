package com.example.admin.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.admin.asynck.DisposeMsgAsynctask;

import static com.example.admin.bean.Final.DISPOSE_MSG;
import static com.example.admin.bean.Final.HTTP;
import static com.example.admin.utils.AlertDialogUtils.getPaymentMethodDialog;

/**
 * Created by ${Kikis} on 2016-09-06.
 */

public class DialogUtils {


   public static void getMessageSendDialog(final Context context, final int Msg, final String csid, final String key, final int position){

       if(Msg==0){
           getPaymentMethodDialog(context,csid,key,position);
       }
       if(Msg==1){
           AddWaterAndurge(context,Msg,csid,key,position);
       }
       if(Msg==2){

           AddWaterAndurge(context,Msg,csid,key,position);

       }
    }



    public static void AddWaterAndurge(final Context context, final int msg, final String csid, final String key, final int position){


        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        if(msg==2){
            builder.setMessage("是否处理催单操作?");
        }
        if(msg==1){
            builder.setMessage("是否处理加水操作?");
        }
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which) {

                new DisposeMsgAsynctask(context).execute(HTTP+DISPOSE_MSG,msg, csid, key,position);

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                return;
            }
        });
        //创建并显示出来
        builder.create().show();



    }

}
