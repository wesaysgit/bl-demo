package com.bolink;

import com.es.encrypt.StringUtils;

public class JhPayQueryTest {

    public static void main(String[] args) {

        String MERCHANTID = "105012158128983";
        String BRANCHID = "320000000";
        String POSID = "094962365";
        String ORDERDATE = "20251211";
        String BEGORDERTIME = "00:00:00";     //订单开始时间
        String ENDORDERTIME = "23:59:59";     //订单结束时间
        String BEGORDERID = "";
        String ENDORDERID = "";
        String QUPWD = "Ccb955330";   //商户账号登录密码
        String TXCODE = "410408";    //业务代码固定
        String SEL_TYPE = "3";        //查询结果返回类型，返回为xml
        String OPERATOR = "";
        String TYPE = "0";
        String KIND = "0";
        String STATUS = "1";
        String ORDERID = "4202512091441099155726";    //订单号
        String PAGE = "1";    //页码
        String CHANNEL = "";
        String bankURL = "https://ibsbjstar.ccb.com.cn/app/ccbMain";
        String params = "MERCHANTID=" + MERCHANTID + "&BRANCHID=" + BRANCHID + "&POSID=" + POSID + "&ORDERDATE="
                + ORDERDATE + "&BEGORDERTIME=" + BEGORDERTIME + "&ENDORDERTIME=" + ENDORDERTIME + "&ORDERID="
                + ORDERID + "&QUPWD=&TXCODE=" + TXCODE + "&TYPE=" + TYPE + "&KIND=" + KIND + "&STATUS=" + STATUS +
                "&SEL_TYPE=" + SEL_TYPE + "&PAGE=" + PAGE + "&OPERATOR=" + OPERATOR + "&CHANNEL=" + CHANNEL;
        String paramsValue = params + "&MAC=" + StringUtils.MD5(params);
        System.out.println(paramsValue);

    }

}
