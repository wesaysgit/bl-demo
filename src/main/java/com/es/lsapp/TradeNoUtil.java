package com.es.lsapp;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TradeNoUtil {
    private static SecureRandom random = new SecureRandom();

    public static String getTradeNo(Integer payCompanyNo){
        SimpleDateFormat sdf_no = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String pre = "";
        if(payCompanyNo != null){
            pre = payCompanyNo+"";
        }
        Calendar calendar = Calendar.getInstance();
        String tradeNo = pre+sdf_no.format(calendar.getTime()) + (int)(random.nextDouble() * 10000000);
        return tradeNo;
    }
    public static String getTradeNoPre(String  pre){
        SimpleDateFormat sdf_no = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        String tradeNo = pre+sdf_no.format(calendar.getTime()) + (int)(random.nextDouble() * 10000000);
        return tradeNo;
    }
}
