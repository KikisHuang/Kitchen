package com.example.admin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.admin.kitchen.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ${Kikis} on 2016-07-22.
 */
public class CommonUtil {

    private static final String TAG = "CommonUtil";

    /**
     * 无标题栏
     */

    public static void setNoTitleBar(Activity context) {

        context.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    /**
     * 全屏效果
     */

    public static void setFullScreen(Activity context) {

        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    /**
     * 退出回收内存
     */
    public static void Exit(Activity context) {

        context.finish();
        System.gc();

    }


    /**
     * 完全退出App方法
     */
    public static void entirelyexit() {
        System.exit(0);
        System.gc();
    }
    /**
     * 联网权限判断
     */
    public static NetworkInfo getNetworkInfo(Activity context) {

        //判断是否联网
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();

        return info;
    }


    /**
     * 获得系统当前年-月-日-时-分-秒
     */
    public static String getDateAndTime() {

        SimpleDateFormat formatter = new SimpleDateFormat   ("yyyyMMddHHmmss");

        Date curDate = new Date(System.currentTimeMillis());//获取当前时间

        return formatter.format(curDate);
    }

    public static String getDate() {

        SimpleDateFormat formatter = new SimpleDateFormat   ("yyyy-MM-dd");

        Date curDate = new Date(System.currentTimeMillis());//获取当前时间

        return formatter.format(curDate);
    }


    public static String getTime() {

        SimpleDateFormat formatter = new SimpleDateFormat   ("HH:mm:ss");

        Date curDate = new Date(System.currentTimeMillis());//获取当前时间

        return formatter.format(curDate);
    }

    /**
     * 跳转方法
     */
    public static void getIntent(Activity ac){

        Intent intent = new Intent(ac,MainActivity.class);

        ac.startActivity(intent);

        CommonUtil.Exit(ac);
    }


    //版本查询
    public static String getVersion(Context context) {

        try {
            PackageManager manager = context.getPackageManager();

            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);

            String version = info.versionName;

            return version;

        } catch (Exception e) {

            e.printStackTrace();
            Log.i(TAG,"错误信息+++++++++++++++++++"+e);
        }
        return null;
    }

   public static int getWindowsManager(String str,Context context){

       WindowManager wm = ((Activity)context).getWindowManager();
       if(str.equals("width")){

           int width = wm.getDefaultDisplay().getWidth();

           return width;
       }

       if(str.equals("height")){

           int height = wm.getDefaultDisplay().getHeight();

           return height;
       }

       return 0;
   }
}
