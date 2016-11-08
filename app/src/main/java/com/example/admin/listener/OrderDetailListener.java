package com.example.admin.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.admin.kitchen.QueryFoodActivity;

/**
 * Created by ${Kikis} on 2016-10-27.
 */

public class OrderDetailListener implements View.OnClickListener {

    private Context context ;
    private String DeskName = "";
    private String CSID = "";
    private String Order_State = "";

    public OrderDetailListener(Context context, String DeskName, String CSID, String Order_State) {
        this.context = context;
        this.DeskName = DeskName;
        this.CSID = CSID;
        this.Order_State = Order_State;
    }

    @Override
    public void onClick(View v) {

        Intent intent  = new Intent(context,QueryFoodActivity.class);

        intent.putExtra("Od_DeskName",DeskName);
        intent.putExtra("CSID",CSID);
        intent.putExtra("Order_State",Order_State);

        context.startActivity(intent);


    }
}
