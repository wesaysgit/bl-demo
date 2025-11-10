package com.es.esdemo;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum PointChannelEnum {

    CMCC("CMCC","移动"),
    CMCC2("CMCC2","移动2"),
    ;

    private String pointChannel;

    private String name;

    PointChannelEnum(String pointChannel, String name) {
        this.pointChannel = pointChannel;
        this.name = name;
    }

    public String getPointChannel() {
        return pointChannel;
    }

    public void setPointChannel(String pointChannel) {
        this.pointChannel = pointChannel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Map<String, String> res = new HashMap<>();
        Arrays.stream(PointChannelEnum.values()).forEach(it -> res.put(it.getPointChannel(), it.getName()));
        System.out.println(JSON.toJSONString(res));
    }
}
