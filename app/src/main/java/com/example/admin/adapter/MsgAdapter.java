package com.example.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.bean.MsgData;
import com.example.admin.kitchen.R;

import java.util.List;

import static com.example.admin.bean.Final.ADD_WARTER;
import static com.example.admin.bean.Final.NEWORDER;
import static com.example.admin.bean.Final.SETTLE;
import static com.example.admin.bean.Final.URGE;
import static com.example.admin.bean.Final.add_warter;
import static com.example.admin.bean.Final.neworder;
import static com.example.admin.bean.Final.settle;
import static com.example.admin.bean.Final.urge;

/**
 * Created by ${Kikis} on 2016-08-31.
 */

public class MsgAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<MsgData> mlist;

    public MsgAdapter(Context context, List<MsgData> mlist) {

        this.context = context;
        this.mlist = mlist;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
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

            view = inflater.inflate(R.layout.message_item, null);

            viewHolder = new ViewHolder();

            viewHolder.DeskID = (TextView) view.findViewById(R.id.DeskID);
            viewHolder.Type = (TextView) view.findViewById(R.id.Type);
            viewHolder.Time = (TextView) view.findViewById(R.id.Time);

            view.setTag(viewHolder);

        }

        viewHolder = (ViewHolder) view.getTag();

        viewHolder.DeskID.setText(mlist.get(position).getDeskID());
        MessageIf(position, viewHolder);

        String t = mlist.get(position).getDateTime();
        String date = t.replace(" ","\n");
        viewHolder.Time.setText(date);

        return view;
    }

    private void MessageIf(int position, ViewHolder viewHolder) {

        int msg = Integer.parseInt(mlist.get(position).getMessage());

        if (SETTLE == msg) {

            viewHolder.Type.setText(settle);
        }
        if (ADD_WARTER == msg) {

            viewHolder.Type.setText(add_warter);
        }
        if (URGE == msg) {

            viewHolder.Type.setText(urge);
        }
        if (NEWORDER == msg) {

            viewHolder.Type.setText(neworder);
        }


    }

    class ViewHolder {
        private TextView DeskID;
        private TextView Type;
        private TextView Time;
    }

}
