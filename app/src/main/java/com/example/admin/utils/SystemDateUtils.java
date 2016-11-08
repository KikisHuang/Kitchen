package com.example.admin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ${Kikis} on 2016-10-25.
 */

public class SystemDateUtils {



    public static String getSystemDateTime(){

        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        return str;
    }


    public static String getSystemDate(){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        String date = sdf.format(new java.util.Date());

        return date;
    }
}
