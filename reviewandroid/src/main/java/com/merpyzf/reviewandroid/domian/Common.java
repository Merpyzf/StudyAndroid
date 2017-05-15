package com.merpyzf.reviewandroid.domian;

/**
 * Created by 春水碧于天 on 2017/5/10.
 */

public class Common {


    //1.设置小车动作
    public  static String url1 = "http://172.22.21.230:8080/transportservice/type/jason/action/SetCarMove.do ";

    public static String params1 = "{\"CarId\":1, \"CarAction\":\"Start\"} ";


    //2.查询小车余额
    public static String url2 = "http://172.22.21.230:8080/transportservice/type/jason/action/GetCarAccountBalance.do";

    public static String params2 = "{\"CarId\":1}";


    //3.小车账户充值

    public static String url3 = "http://172.22.21.230:8080/transportservice/type/jason/action/SetCarAccountRecharge.do";
    public static String params3 = "{\"CarId\":5,\"Money\":200}";

    //4.设置红绿灯的状态
    public static String url4 = "http://172.22.21.230:8080/transportservice/type/jason/action/SetTrafficLightNowStatus.do";
    public static String params4 = "{\"TrafficLightId\":\"1-1\",\"Status\":\"Green\",\"Time\":55}";

    //5.查询红绿灯的状态

    public static String url5 = "http://172.22.21.230:8080/transportservice/type/jason/action/GetTrafficLightNowStatus.do";
    public static String params5 = "{\"TrafficLightId\":\"1-1\"}";

    //6.设置红绿灯的配置信息

    public static String url6 = "http://172.22.21.230:8080/transportservice/type/jason/action/SetTrafficLightConfig.do";
    public static String params6 = "{\"TrafficLightId\":2,\"RedTime\":25,\"GreenTime\":55,\"YellowTime\":5}";

    //7.查询红绿灯的配置信息

    public static String url7 = "http://172.22.21.230:8080/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
    public static String params7 = "{\"TrafficLightId\":2}";

    //接口8：设置路灯的手动/自动控制模式

    public static String url8 = "http://172.22.21.230:8080/transportservice/type/jason/action/SetRoadLightControlMode.do";
    public static String params8 = "{\"ControlMode\":\"Manual\"} ";

    //接口9：手动开关指定路灯

    public static String url9 = "http://172.22.21.230:8080/transportservice/type/jason/action/SetRoadLightStatusAction.do";
    public static String params9 = "{\"RoadLightId\":1,\"Action\":\"Close\"} ";


    //接口10：查询当前路灯的状态

    public static String url10 = "http://172.22.21.230:8080/transportservice/type/jason/action/GetRoadLightStatus.do ";
    public static String params10 = "{\"RoadLightId\":3}";

    //接口11：查询所有传感器的当前值

    public static String url11 = "http://172.22.21.230:8080/transportservice/type/jason/action/GetAllSense.do";
    public static String params11 = "";

    //接口12：查询单个传感器的当前值

    public static String url12 = "http://172.22.21.230:8080/transportservice/type/jason/action/GetSenseByName.do";
    public static String params12 = "{\"SenseName\":\"temperature\"} ";

    //接口13：查询单个传感器的当前值

    public static String url13 = "http://172.22.21.230:8080/transportservice/type/jason/action/GetBusCapacity.do";
    public static String params13 = "{\"BusId\":100}";

    //接口14：查询道路拥挤状态
    public static String url14 = "http://172.22.21.230:8080/transportservice/type/jason/action/GetRoadStatus.do";
    public static String params14 = "{\"RoadId\":12}";


}
