package com.example.admin.kitchen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.adapter.MsgAdapter;
import com.example.admin.asynck.QueryThread;
import com.example.admin.bean.MsgData;
import com.example.admin.utils.SoundPoolUtils;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.admin.bean.Final.HTTP;
import static com.example.admin.bean.Final.MESSAGE_QUERY;
import static com.example.admin.bean.Final.ONE_START_THERAD;
import static com.example.admin.bean.Final.THREADCONTROL;
import static com.example.admin.utils.DialogUtils.getMessageSendDialog;
import static com.example.admin.utils.MyIntent.getmyIntent;
import static com.example.admin.utils.SharedPreference.removeSharedPreference;

public class MainActivity extends Activity {
    private ListView listView;
    private RelativeLayout rl;
    private TextView title;
    private static MsgAdapter adpater;
    private Handler mHandler;
    private QueryThread QT;
    private static List<MsgData> mlist;
    private Button query_bt;
    private SoundPoolUtils playSoundPool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setTitle();
        getHandler();
        getData();
        onitemclick();
        queryclick();
        Log.i(TAG, "onCreate ");

    }

    private void queryclick() {

        query_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        //getEditextDialog(MainActivity.this);
                Intent intent  = new Intent(MainActivity.this,AllDeskActivity.class);
                startActivity(intent);

            }
        });

    }

    private void onitemclick() {


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TAG, "Click == " + mlist.get(position).getMessage());
                Log.i(TAG, "Key == " + mlist.get(position).getKey());

                getMessageSendDialog(MainActivity.this, Integer.parseInt(mlist.get(position).getMessage()), mlist.get(position).getCSID(), mlist.get(position).getKey(), position);

            }
        });


    }

    private void getHandler() {

        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


                if (ONE_START_THERAD) {
                    ONE_START_THERAD = false;
                    mlist = (List<MsgData>) msg.getData().getSerializable("mlist");

                    if(mlist.size()>0){

                        Log.i(TAG, "成功接收消息,调用 handleMessage-----");
                        adpater = new MsgAdapter(MainActivity.this, mlist);

                        listView.setAdapter(adpater);

                        Log.i(TAG, "第一次的获得数据,调用setAdapter");

                        playSoundPool.play(1, 0);
                    }

                } else {

                    List<MsgData> blist = (List<MsgData>) msg.getData().getSerializable("mlist");
                    Log.i(TAG, "测试-----输出集合长度====" + blist.size());

                    if (blist.size() > 0) {

                        if (mlist.size() <= 0) {

                            mlist = (List<MsgData>) msg.getData().getSerializable("mlist");

                            adpater = new MsgAdapter(MainActivity.this, mlist);

                            listView.setAdapter(adpater);
//                            adpater.notifyDataSetChanged();
                            Log.i(TAG, "空消息列表,有新消息添加");
                            playSoundPool.play(1, 0);
                        } else {

                            String a = blist.get(blist.size() - 1).getCSID();
                            String a1 = blist.get(blist.size() - 1).getMessage();
                            String a2 = blist.get(blist.size() - 1).getDateTime();


                            String b = mlist.get(mlist.size() - 1).getCSID();
                            String b1 = mlist.get(mlist.size() - 1).getMessage();
                            String b2 = mlist.get(mlist.size() - 1).getDateTime();


                            if (blist.size() == mlist.size() && a.equals(b) && a1.equals(b1) && a2.equals(b2)) {

                                Log.i(TAG, "获得的数据没有修改无需任何操作，，，，，，mlist.size==="+mlist.size());

                            } else {

                                mlist = (List<MsgData>) msg.getData().getSerializable("mlist");

                                adpater = new MsgAdapter(MainActivity.this, mlist);

                                listView.setAdapter(adpater);
                                Log.i(TAG, "获得的数据有更新");
                                playSoundPool.play(1, 0);
                            }
                            return;
                        }

                    } else {

                        mlist = (List<MsgData>) msg.getData().getSerializable("mlist");

                        adpater = new MsgAdapter(MainActivity.this, mlist);

                        listView.setAdapter(adpater);

                        Log.i(TAG, "消息列表为空..");
                    }

                }

            }
        };
    }

    private void setTitle() {
        title.setText(R.string.title_text);
    }

    private void init() {

        ONE_START_THERAD  = true;
        THREADCONTROL = true;
        QT = null;
        mlist = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);
        rl = (RelativeLayout) findViewById(R.id.title_rl);
        title = (TextView) findViewById(R.id.title);
        query_bt = (Button) findViewById(R.id.query_bt);

        //初始化音效播放;
        playSoundPool = new SoundPoolUtils(MainActivity.this);
        playSoundPool.loadSfx(R.raw.ding, 1);
//      playSoundPool.play(1, 0);
    }

    public void getData() {


        String TypeID = getmyIntent(MainActivity.this,"TypeID");

        if (!TypeID.isEmpty()) {

            if(QT==null&&ONE_START_THERAD){

                QT = new QueryThread(TypeID, HTTP + MESSAGE_QUERY, mHandler);

                QT.start();

            }else{

                Log.i(TAG,"已有线程查询,无需启动。");
                return;
            }

//            CommonAsynctask Comm = (CommonAsynctask) new CommonAsynctask(MainActivity.this).execute(TypeID,HTTP+MESSAGE_QUERY);

//            Comm.setMessageResponse(new MessageAskResponse() {
//                @Override
//                public void onMessageReceivedSuccess(List<MsgData> mlist) {
//
//
//                    Log.i(TAG,"成功,调用 onMessageReceivedSuccess-----");
//                    adpater = new MsgAdapter(MainActivity.this,mlist);
//
//                    listView.setAdapter(adpater);
//
//                }
//
//                @Override
//                public void onMessageReceivedFailed() {
//
//
//                    Log.i(TAG,"失败,调用 onMessageReceivedSuccess-----");
//                }
//            });
        }
        Log.i(TAG, "TypeID -----" + TypeID);

    }

    /**
     * 删除操作;
     * @param position
     */
    public static void Succeed(int position) {

        mlist.remove(position);
        adpater.notifyDataSetChanged();
        Log.i(TAG, "删除mlist中处理的数据");
        return;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void ThreadStop() {

        THREADCONTROL = false;
        QT = null;
        System.exit(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ONE_START_THERAD = true;
        ThreadStop();
        CleanSharedPreFerence();
        Log.i(TAG, "onDestroy");
    }

    private void CleanSharedPreFerence() {
        removeSharedPreference(this,"TypeID");
    }
}
