package com.bolink.util;

import java.util.*;

public class GenerateInsertSQL {
    public static void main(String[] args) {

        // 你提供的 id 和 comid 集合（数量应相同）
        List<Long> ids = new ArrayList<>();

        for (int i = 0; i < 66; i++) {
            Long payid = SnowFlakeIdUtil.getInstance().nextId(SnowFlakeIdUtil.ParkRoute);
            ids.add(payid);
        }

        List<Integer> comids = Arrays.asList(
                550879,550880,551022,551429,551902,551924,551945,551953,551990,552021,552035,552482,552505,552657,552698,552910,552915,553158,553326,553534,553539,553541,554175,554279,554283,554381,554382,554388,554390,554392,554494,554507,554518,554523,554530,554692,554706,554707,554708,554721,554723,554791,554845,555161,555206,555208,555209,555320,555539,555887,555938,556096,556133,556142,556149,556655,556842,556862,556863,556967,557085,557201,557624,557870,557874,658027
        );

        // 公共字段值
        long payChannelId = 1760579862502558L;
        long paySenceId = 1760580276922086L;
        long utime = 1760939955L;

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO \"public\".\"park_pay_route_tb\" ")
          .append("(\"id\", \"pay_sence_no\", \"pay_channel_id\", \"status\", \"rate\", \"comid\", \"remark\", \"pay_sence_id\", \"my_rate\", \"utime\", \"sbtime\")\n")
          .append("VALUES\n");

        for (int i = 0; i < ids.size(); i++) {
            sb.append("(")
              .append(ids.get(i)).append(", ")
              .append("NULL, ")
              .append(payChannelId).append(", ")
              .append("1, NULL, ")
              .append(comids.get(i)).append(", ")
              .append("'', ")
              .append(paySenceId).append(", ")
              .append("'-1.00000', ")
              .append(utime).append(", ")
              .append("NULL")
              .append(")");
            if (i < ids.size() - 1) {
                sb.append(",\n");
            } else {
                sb.append(";\n");
            }
        }

        // 输出生成的 SQL
        System.out.println(sb);
    }
}
