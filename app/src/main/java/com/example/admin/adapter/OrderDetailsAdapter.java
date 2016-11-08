package com.example.admin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.bean.OrderBean;
import com.example.admin.kitchen.R;
import com.example.admin.listener.OrderDetailListener;

import java.util.List;

import static com.example.admin.kitchen.R.id.state;
import static com.example.admin.kitchen.R.id.time;

/**
 * Created by ${Kikis} on 2016-10-25.
 */
public class OrderDetailsAdapter extends RecyclerView.Adapter {
    private Context context;
    private  List<OrderBean> list;
    private String DeskName ;

    public OrderDetailsAdapter(Context context, List<OrderBean> list, String deskname) {

        this.list = list;
        this.context = context;
        this.DeskName = deskname;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderDetailsAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.order_recycler_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView sate = ((OrderDetailsAdapter.MyViewHolder)holder).satetv;
        TextView time = ((OrderDetailsAdapter.MyViewHolder)holder).timetv;

        int state = Integer.parseInt(list.get(position).getState());
        if(state==0){
            sate.setText("未结账");
        }
        if(state==9){

            sate.setText("已结账");
        }
        String status = list.get(position).getState();
        String csid = list.get(position).getCSID();
        time.setText(list.get(position).getTime());

        time.setOnClickListener(new OrderDetailListener(context,DeskName,csid,status));
        sate.setOnClickListener(new OrderDetailListener(context,DeskName,csid,status));
    }

    @Override
    public int getItemCount() {
        return  list==null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView satetv;
        public TextView timetv;

        public MyViewHolder(View v) {
            super(v);
            satetv = (TextView) v.findViewById(state);
            timetv = (TextView) v.findViewById(time);

        }
    }
}
