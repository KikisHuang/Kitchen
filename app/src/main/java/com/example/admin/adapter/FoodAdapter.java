package com.example.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.bean.Foodbean;
import com.example.admin.kitchen.R;

import java.util.List;

/**
 * Created by ${Kikis} on 2016-09-07.
 */
public class FoodAdapter extends BaseAdapter {
    private Context context;
    private List<Foodbean> list;
    private LayoutInflater inflater;

    public FoodAdapter(Context context, List<Foodbean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        View view = convertView;

        if (view == null) {

            view = inflater.inflate(R.layout.food_item, null);

            viewHolder = new ViewHolder();

            viewHolder.Foodname = (TextView) view.findViewById(R.id.Foodname);

            viewHolder.Foodnum = (TextView) view.findViewById(R.id.Foodnum);

            viewHolder.Foodprice = (TextView) view.findViewById(R.id.Foodprice);

            view.setTag(viewHolder);

        }

        viewHolder = (ViewHolder) view.getTag();


        if(list.get(position).getDetail().equals("默认")){

            viewHolder.Foodname.setText(list.get(position).getFoodName());
        }else{

            viewHolder.Foodname.setText(list.get(position).getFoodName()+"\n"+"("+list.get(position).getDetail()+")");

        }
        viewHolder.Foodnum.setText(String.valueOf(list.get(position).getFoodNumber()));

        viewHolder.Foodprice.setText(list.get(position).getSellPrice());


        return view;
    }


    class ViewHolder {
        private TextView Foodname;
        private TextView Foodnum;
        private TextView Foodprice;
    }
}
