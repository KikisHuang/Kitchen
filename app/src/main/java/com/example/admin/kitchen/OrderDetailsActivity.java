package com.example.admin.kitchen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.adapter.OrderDetailsAdapter;
import com.example.admin.asynck.QueryoOrderDetailsAsynck;
import com.example.admin.bean.OrderBean;

import java.util.List;

import static com.example.admin.bean.Final.HTTP;
import static com.example.admin.bean.Final.QUERY_Order_Details;

/**
 * Created by ${Kikis} on 2016-10-25.
 */
public class OrderDetailsActivity extends Activity{

    private static RecyclerView recycler;
    private TextView return_tv;
    String DeskName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_layout);
            init();
            getDeskName();
            outpage();
    }

    private void outpage() {
        return_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void query() {

        if(!DeskName.isEmpty()){

            new QueryoOrderDetailsAsynck(this).execute(DeskName,HTTP+QUERY_Order_Details);

        }else{

            Toast.makeText(this,"没有获得桌号,无法查询订单明细..", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void getDeskName() {

        DeskName = getIntent().getStringExtra("DeskName");
    }

    private void init() {

        recycler = (RecyclerView) findViewById(R.id.RecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        return_tv = (TextView) findViewById(R.id.return_tv);
    }


    public static void setData(Context context, List<OrderBean> list, String deskname){

        recycler.setAdapter(new OrderDetailsAdapter(context,list,deskname));
    }

    @Override
    protected void onResume() {
        super.onResume();
        query();
    }
}
