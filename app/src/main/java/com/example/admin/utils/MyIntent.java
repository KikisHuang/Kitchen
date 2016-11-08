package com.example.admin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.admin.bean.Foodbean;
import com.example.admin.kitchen.MainActivity;
import com.example.admin.kitchen.QueryFoodActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${Kikis} on 2016-08-31.
 */

public class MyIntent {

    /**
     * Welcom跳转Main;
     */
    public static void setIntent(Activity ac, String Msg, String value) {

        Intent intent = new Intent(ac, MainActivity.class);

        intent.putExtra(Msg, value);

        ac.startActivity(intent);

        CommonUtil.Exit(ac);
    }

    /**
     * Main跳转Query;
     */
    public static void setIntent1(Context ac, String Msg, List<Foodbean> value,String num) {

        Intent intent = new Intent(ac, QueryFoodActivity.class);

        intent.putExtra(Msg, (Serializable) value);
        intent.putExtra("number",num);

        ac.startActivity(intent);

    }

    /**
     * Main跳转Query;
     */
    public static List getmyIntentList(Activity ac, String Msg) {

        return (List) ac.getIntent().getSerializableExtra(Msg);

    }

    /**
     * Main跳转Query;
     */
    public static String getmyIntentString(Activity ac, String Msg) {

        return  ac.getIntent().getStringExtra(Msg);

    }

    /**
     * Welcom跳转Main;
     */
    public static String getmyIntent(Activity ac, String Msg) {


        String id = String.valueOf(ac.getIntent().getStringExtra(Msg));

        return id;

    }
}
