package com.es.log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LogTest {

    public static void main(String[] args) {
        BigDecimal profitFee = BigDecimal.valueOf(Double.parseDouble("35"))
                .multiply(new BigDecimal("0.0003"))
                .setScale(2, RoundingMode.HALF_UP);
        System.out.println(profitFee);
        boolean b = profitFee.compareTo(BigDecimal.ZERO) > 0;
        System.out.println(b);
    }

    public static Map getUserInfoFromPass(Long unionId, String platId) {
        Map retMap = new HashMap();
        int time = 0;
        while (time < 50) {
            Random random = new Random();
            int i = random.nextInt(20);
            if (i == 19) {
                retMap.put("username", 11);
                retMap.put("password", 22);
                retMap.put("state", 1);
                System.out.println(time+">>>>success>>>>>>>>>>>"+i);
                break;
            } else {
                System.out.println(time+">>>>fail>>>>>>>>>>>"+i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
            }
        }
        return retMap;
    }

    public static void main1(String[] args) {
        Map<String, Object> fieldParams = new HashMap<String, Object>();
        fieldParams.put("name", "张三");
        fieldParams.put("address", "查明和");
        fieldParams.put("phone", "18827688654");
        fieldParams.put("state", "1");
        fieldParams.put("mobile", "18827688654");
        fieldParams.put("isdistibution", "1");
        fieldParams.put("docking_type", "0");
        fieldParams.put("plat_type","0");
        fieldParams.put("share_percent", "10.1");
        fieldParams.put("ad_share_percent", "10.23");
        fieldParams.put("share_percent_type", "2");
        fieldParams.put("bd_id",1);
        fieldParams.put("afficated_union",null);

        Map<String, Object> oldMap = new HashMap<String, Object>();
        oldMap.put("name", "李四");
        oldMap.put("address", "查明和123");
        oldMap.put("phone", "13044259884");
        oldMap.put("state", "1");
        oldMap.put("mobile", "18827688656");
        oldMap.put("isdistibution", "0");
        oldMap.put("docking_type", "1");
        oldMap.put("plat_type","1");
        oldMap.put("share_percent", "10.0");
        oldMap.put("ad_share_percent", "10.0");
        oldMap.put("share_percent_type", "1");
        oldMap.put("bd_id",1);
        oldMap.put("afficated_union",null);
        Map<String, String> note = new HashMap<>();
        note.put("name", "名称");
        note.put("address", "地址");
        note.put("phone", "联系电话");
        note.put("state", "状态");
        note.put("mobile", "密保手机");
        note.put("isdistibution", "支持清分");
        note.put("docking_type", "对接模式");
        note.put("plat_type","厂商类型");
        note.put("share_percent", "手续费分润比例");
        note.put("ad_share_percent", "广告费分润比例");
        note.put("share_percent_type", "分润比例相关设置状态");
        note.put("bd_id","商务BD");
        note.put("afficated_union","关联厂商");

        String s = compareMap(oldMap, fieldParams, note, new StringBuilder("厂商(200768)中的"));
        System.out.println("s = " + s);
    }

    public static String compareMap(Map<String, Object> oldMap, Map<String, Object> newMap, Map<String, String> noteMap, StringBuilder builder) {
        if (newMap.isEmpty()) return "未检测到修改";
        try {
            newMap.keySet().forEach(key -> {
                String note = noteMap.get(key);
                Object oldValue = oldMap.get(key) == null ? "空" : oldMap.get(key);
                Object newValue = newMap.get(key) == null ? "空": newMap.get(key);
                if (!(oldValue instanceof List) && !(newValue instanceof List)) {
                    if (!oldValue.equals(newValue)) {
                        builder.append(note).append("由").append(oldValue).append("修改为").append(newValue).append(",");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-拼接日志描述错误");
        }
        return builder.toString();
    }
}
