package com.example.admin.bean;

/**
 * Created by ${Kikis} on 2016-08-30.
 */

public class Final {

    public static final String VERIFY_KEY = "G*Pf8UrW$rdP4oeA0ObzKoQhN3Gn1fS7";

//    public static final String HTTP = "http://120.76.144.49:8080/MenuSystem";

    public static final String HTTP = "http://192.168.1.88:8080/MenuSystem";

    //版本号查询;
    public static final String VERSION_QUERY="/VersionQuery";
    public static final String APK = ".apk";

    //Apk下载;
    public static final String APK_DOWNLOAD="http://192.168.1.88:8080/S_version/";

    public static final String DESK_TYPE = "/DeskType";

    public static final String DISPOSE_MSG = "/DisposeMsg";

    public static final String QUERY_FOOD = "/KitchenQueryFood";

    public static final String MESSAGE_QUERY = "/MessageQuery";

    public static final String QUERY_DESK ="/QueruyDesk";

    public static final String QUERY_Order_Details ="/QueryOrderDetails";

    /**
     * 动画判断符;0开始、1结束;
     */
    public static final int START = 0;
    public static final int END = 1;

    /**
     * 消息状态判断;0：结账，1：加水，2：催单
     */
    public static final int SETTLE = 0;
    public static final int ADD_WARTER = 1;
    public static final int URGE = 2;

    public static final String settle = "结账";
    public static final String add_warter = "加水";
    public static final String urge = "催单";

    /**
     * 查询上菜状态判断;1：已下单，9：已上菜;
     */

    public static final String ORDER_TRUE = "已下单";
    public static final int TRUE_ORDER = 1;

    public static final String ORDER_RECEIVER= "后厨已接收";
    public static final int RECEIVER_ORDER = 2;

    public static final String ORDER_CANCEL = "已取消";
    public static final int CANCEL_ORDER = 99;

    /**
     * 消息成功标识符
     */
    public static final int MSG_SUCCESS = 0;//成功
    public static final int MSG_FAILURE = 1;//失败


    /**
     *第一次启动标识符
     */

    public static  boolean ONE_START_THERAD = true;

    /**
     *线程标识符
     */
    public static  boolean THREADCONTROL = true;
}
