package cn.ne.aopauth.utils.resutils;

import java.util.HashMap;
import java.util.Map;

public class SystemConstants {

    // success or failure
    public static final String SUCCESS = "正确！";
    public static final String FAILURE = "错误！";

    // CODE change to string
    public static final String CODE_1 = "1";
    public static final String CODE_0 = "0";

    // 错误编号
    public static final String ENULL = "ENULL";
    public static final String E000 = "E000";
    public static final String E001 = "E001";
    public static final String E002 = "E002";
    public static final String E003 = "E003";
    public static final String E004 = "E004";
    public static final String E005 = "E005";



    // 错误信息
    public static Map<String, String> getMessage() {
        Map<String,String> msgMap = new HashMap<>();
        msgMap.put(ENULL,"内容不能为空。");

        msgMap.put(E000,"一切正常。");
        msgMap.put(E001,"信息:");
        msgMap.put(E002,"令牌已无效或不存在。");
        msgMap.put(E003,"用户已停用或不存在。");
        msgMap.put(E004,"用户名或密码不正确。");


        return msgMap;
    }

    /**
     * @return 订单修改字段对应码表
     */
    public static Map<String,String> properitsToFeild(){

        Map<String,String> feildMap=new HashMap<>();
        feildMap.put("","");

        return feildMap;
    }


}
