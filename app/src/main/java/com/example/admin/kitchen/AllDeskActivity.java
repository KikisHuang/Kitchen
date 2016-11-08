package com.example.admin.kitchen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.adapter.RecyclerAdapter;
import com.example.admin.adapter.SpaceItemDecoration;
import com.example.admin.asynck.QueryDeskAsynctask;

import java.util.List;

import static com.example.admin.bean.Final.HTTP;
import static com.example.admin.bean.Final.QUERY_DESK;
import static com.example.admin.utils.SharedPreference.getSahrePreference;

/**
 * Created by ${Kikis} on 2016-10-25.
 */
public class AllDeskActivity extends Activity{
    private static RecyclerView recycler;
    private TextView return_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alldesk_layout);
        init();
        recyclerSetting();
        getData();
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

    private void getData() {

        String TypeId = getSahrePreference(this,"TypeID");

        if(TypeId.isEmpty()){

            Toast.makeText(this, "没有获得菜单类型...请重启应用", Toast.LENGTH_SHORT).show();
        }else{


            new QueryDeskAsynctask(this).execute(TypeId,HTTP+QUERY_DESK);

        }

    }

    private void recyclerSetting() {

        recycler.setLayoutManager(new GridLayoutManager(this, 4));
        recycler.addItemDecoration(new SpaceItemDecoration(4));

    }

    public static void setData(Context context,List<String> list) {

        recycler.setAdapter(new RecyclerAdapter(context,list));
    }

    private void init() {

        recycler = (RecyclerView) findViewById(R.id.RecyclerView);
        return_tv = (TextView) findViewById(R.id.return_tv);
    }


}
