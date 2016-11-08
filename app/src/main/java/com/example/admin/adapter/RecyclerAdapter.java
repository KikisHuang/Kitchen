package com.example.admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.kitchen.OrderDetailsActivity;
import com.example.admin.kitchen.R;

import java.util.List;

/**
 * Created by ${Kikis} on 2016-10-25.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {
    private List<String> datas;
    private Context context;

    public RecyclerAdapter(Context context,List<String> datas) {

        this.datas = datas;
        this.context = context;
    }
    @Override
    public int getItemCount() {
        return datas==null ? 0 : datas.size();
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView t = ((MyViewHolder)holder).tv;

        t.setText(datas.get(position));
        t.setTextColor(context.getResources().getColor(R.color.black9));
        t.setTextSize(18);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_view, parent, false));
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public MyViewHolder(View v) {
            super(v);
            tv = (TextView) v.findViewById(R.id.tv);

            tv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

//              Toast.makeText(context, getPosition() + "", Toast.LENGTH_SHORT).show();

                    Intent intent  = new Intent(context,OrderDetailsActivity.class);

                    intent.putExtra("DeskName",datas.get(getPosition()));

                    context.startActivity(intent);
                }
            });
        }
    }

}
