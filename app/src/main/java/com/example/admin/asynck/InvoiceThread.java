package com.example.admin.asynck;

import android.util.Log;

import com.example.admin.bean.Bill;
import com.example.admin.bean.Foodbean;
import com.example.admin.utils.Pos;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ${Kikis} on 2016-09-06.
 */

public class InvoiceThread extends Thread {
    private Pos pos;
    private List<Bill> blist;
    private List<Foodbean> fblist;
    private String csid;
    private String table;
    private String status;
    private String date;
    private String way;
    private double all;
    private  String typeIp;

    public InvoiceThread(List<Bill> blist, String typeIp) {

        this.blist = blist;
        this.typeIp = typeIp;
    }

    @Override
    public void run() {
        Log.i(TAG, "进入起线程打印小票页面...");
        //初始数据取值;
        initData();

        try {
            //IP,端口,编码格式;
            pos = new Pos(typeIp, 9100, "GBK");

            //初始化打印机;
            pos.initPos();

            pos.printTextNewLine("******************谢谢惠顾********************");
            pos.printLine(2);
            pos.bold(true);
            pos.printTabSpace(2);
            pos.printWordSpace(1);
            pos.printText("老楷茶餐厅");

            pos.printLocation(0);
            pos.printTextNewLine("----------------------------------------------");
            pos.bold(false);
            pos.printTextNewLine("订 单 号：" + csid);
            pos.printTextNewLine("桌    号：" + table);
            pos.printTextNewLine("订单状态：" + status);
            pos.printTextNewLine("订单日期：" + date);
            pos.printTextNewLine("付款方式：" + way);
            pos.printLine(2);

            pos.printText("品项");
            pos.printLocation(20, 1);
            pos.printText("单价");
            pos.printLocation(99, 1);
            pos.printWordSpace(1);
            pos.printText("数量");
            pos.printWordSpace(3);
            pos.printText("小计");
            pos.printTextNewLine("----------------------------------------------");


            for (int i = 0; i < blist.size(); i++) {

                double xiaoji = blist.get(i).getPrice() * blist.get(i).getNumber();

                pos.printTextNewLine(blist.get(i).getFoodName());
                pos.printLocation(20, 1);
                pos.printText(String.valueOf(blist.get(i).getPrice()));
                pos.printLocation(99, 1);
                pos.printWordSpace(1);
                pos.printText(String.valueOf(blist.get(i).getNumber()));
                pos.printWordSpace(3);
                pos.printText(String.valueOf(xiaoji));

            }


            pos.printTextNewLine("----------------------------------------------");
            pos.printLine(1);
            pos.printLocation(99, 1);
            pos.printWordSpace(3);
            pos.printText("总价:" + all);

            pos.printLine(1);
            pos.printTextNewLine("******************谢谢惠顾********************");

            pos.printLine(1);
            //打印二维码
            //pos.qrCode("http://blog.csdn.net/haovip123");

            //切纸
            pos.feedAndCut();


            //第二份;
            pos.printTextNewLine("******************谢谢惠顾********************");
            pos.printLine(2);
            pos.bold(true);
            pos.printTabSpace(2);
            pos.printWordSpace(1);
            pos.printText("港式茶餐厅");

            pos.printLocation(0);
            pos.printTextNewLine("----------------------------------------------");
            pos.bold(false);
            pos.printTextNewLine("订 单 号：" + csid);
            pos.printTextNewLine("桌    号：" + table);
            pos.printTextNewLine("订单状态：" + status);
            pos.printTextNewLine("订单日期：" + date);
            pos.printTextNewLine("付款方式：" + way);
            pos.printLine(2);

            pos.printText("品项");
            pos.printLocation(20, 1);
            pos.printText("单价");
            pos.printLocation(99, 1);
            pos.printWordSpace(1);
            pos.printText("数量");
            pos.printWordSpace(3);
            pos.printText("小计");
            pos.printTextNewLine("----------------------------------------------");


            for (int i = 0; i < blist.size(); i++) {

                double xiaoji = blist.get(i).getPrice() * blist.get(i).getNumber();

                pos.printTextNewLine(blist.get(i).getFoodName());
                pos.printLocation(20, 1);
                pos.printText(String.valueOf(blist.get(i).getPrice()));
                pos.printLocation(99, 1);
                pos.printWordSpace(1);
                pos.printText(String.valueOf(blist.get(i).getNumber()));
                pos.printWordSpace(3);
                pos.printText(String.valueOf(xiaoji));

            }


            pos.printTextNewLine("----------------------------------------------");
            pos.printLine(1);
            pos.printLocation(99, 1);
            pos.printWordSpace(3);
            pos.printText("总价:" + all);

            pos.printLine(1);
            pos.printTextNewLine("******************谢谢惠顾********************");

            pos.printLine(1);
            //打印二维码
            //pos.qrCode("http://blog.csdn.net/haovip123");

            //切纸
            pos.feedAndCut();


            pos.closeIOAndSocket();
            pos = null;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initData() {

        csid = blist.get(0).getCSID();
        table = blist.get(0).getTableNumber();
        status = "订单结束";
        date = blist.get(0).getOrderDateTime();
        way = "线下支付";

        for (int i = 0; i < blist.size(); i++) {

            double a = blist.get(i).getPrice();
            int b = blist.get(i).getNumber();

            all += a * b;

        }
    }
}
