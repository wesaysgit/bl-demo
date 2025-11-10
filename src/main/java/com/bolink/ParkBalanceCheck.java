package com.bolink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParkBalanceCheck {

    public static void main(String[] args) throws Exception {
        // 读取 JSON 文件内容
        String content = new String(Files.readAllBytes(Paths.get("/Users/xugan/Downloads/Explore-logs.json")));
        // 解析 JSON
        JSONArray jsonArray = JSONObject.parseArray(content);
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("记账返回>>>>>\\d+");
        for (Object o : jsonArray) {
            JSONObject obj = (JSONObject) o;
            String line = obj.getString("line");
            // 使用正则匹配交易号
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                // 提取交易号部分
                String match = matcher.group();
                String tradeNo = match.replace("记账返回>>>>>", ""); // 去掉前缀
                System.out.println("提取的交易号: " + tradeNo);
                list.add(tradeNo);
            } else {
                System.out.println("未找到交易号");
            }
        }
        // 使用 Map 统计次数
        Map<String, Long> countMap = list.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        // 提取重复元素（出现次数 > 1）
        List<String> duplicates = countMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("重复的元素: " + duplicates);
    }
    
}
