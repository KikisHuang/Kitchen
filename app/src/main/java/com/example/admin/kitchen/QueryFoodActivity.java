package com.example.admin.kitchen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.adapter.FoodAdapter;
import com.example.admin.asynck.InvoiceThread;
import com.example.admin.asynck.QueryFoodAsynck;
import com.example.admin.bean.Bill;
import com.example.admin.bean.Foodbean;
import com.example.admin.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.bean.Final.HTTP;
import static com.example.admin.bean.Final.QUERY_FOOD;
import static com.example.admin.utils.AlertDialogUtils.getPaymentMethodDialog;
import static com.example.admin.utils.MyIntent.getmyIntentString;

/**
 * Created by ${Kikis} on 2016-09-07.
 */
public class QueryFoodActivity extends Activity {
    private static final String TAG = "QueryFoodActivity";
    private TextView title;
    private TextView return_tv;
    private TextView Print_tv;
    private static TextView all_price;
    private static ListView listView;
    private static List<Foodbean> sfblist;
    private List<Bill> blist;
    private static FoodAdapter fadapter;
    private String num;
    private String deskname="";
    private String CSID ="";
    private int State = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queryfood_activity);
        init();
        settitle();
        receiver();
        queryOrderFood();
        click();
        PayClick();
    }

    private void PayClick() {


        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(State!=100&&State==0){

                    getPaymentMethodDialog(QueryFoodActivity.this, CSID,"10086",400);
                }
                if(State==9){

                    Toast.makeText(QueryFoodActivity.this,"订单状态为已结账..无法进行此操作..", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    private static void setAllPrice(List<Foodbean> fblist) {

        double all = 0;
        for(int i=0;i<fblist.size();i++){

            double a = Double.parseDouble(fblist.get(i).getSellPrice());
            int b = fblist.get(i).getFoodNumber();

           all += a * b;

        }
        all_price.setText(String.valueOf(all));
    }

    private void queryOrderFood() {

        if(!deskname.isEmpty()&&!CSID.isEmpty()){

                new QueryFoodAsynck(this).execute(HTTP+QUERY_FOOD,deskname,CSID);

        }   else{

            Toast.makeText(this, "没有获取到查询的订单号", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private void click() {

        return_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonUtil.Exit(QueryFoodActivity.this);
            }
        });
        Print_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                blist = new ArrayList<Bill>();

                if(sfblist.size()>0){

                    for(int i =0;i<sfblist.size();i++){

                        if(sfblist.get(i).getFoodState()!=99){

                            Bill bi  = new Bill();

                            bi.setTableNumber(num);
                            bi.setCSID(sfblist.get(i).getCSID());
                            bi.setOrderDateTime(sfblist.get(i).getDateTime());
                            bi.setFoodName(sfblist.get(i).getFoodName());
                            bi.setNumber(sfblist.get(i).getFoodNumber());
                            bi.setPrice(Double.parseDouble(sfblist.get(i).getSellPrice()));

                            blist.add(bi);
                        }

                    }
                    new InvoiceThread(blist,sfblist.get(0).getTypeIP()).start();
                }

            }
        });
    }

    private void receiver() {

//        fblist = getmyIntentList(this, "fblist");
//
//        num = getmyIntentString(this, "number");
         deskname = getmyIntentString(this, "Od_DeskName");

         CSID = getmyIntentString(this, "CSID");

         State = Integer.parseInt(getmyIntentString(this,"Order_State"));
    }

    private void settitle() {
        title.setText("结账");
    }

    private void init() {

        title = (TextView) findViewById(R.id.title);

        return_tv = (TextView) findViewById(R.id.return_tv);

        Print_tv = (TextView) findViewById(R.id.Print_tv);

        listView = (ListView) findViewById(R.id.listView);

        all_price = (TextView) findViewById(R.id.all_price);

        sfblist  = new ArrayList<>();

    }


    public static void setData(Context context ,List<Foodbean> fblist){
        sfblist  = fblist;
        setAllPrice(fblist);
        fadapter = new FoodAdapter(context, fblist);

        listView.setAdapter(fadapter);

    }

}
