package com.es.pojo;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class StreamToMap {

    public static void main(String[] args) {
        ArrayList<UnionParkTb> parkTbs = new ArrayList<>();
        UnionParkTb parkTb = new UnionParkTb();
        parkTb.setId(1L);
        parkTb.setName("name1");
        parkTbs.add(parkTb);
        UnionParkTb parkTb2 = new UnionParkTb();
        parkTb2.setId(2L);
        parkTb2.setName("name2");
        parkTbs.add(parkTb2);
        UnionParkTb parkTb3 = new UnionParkTb();
        parkTb3.setId(3L);
        parkTb3.setName("name3");
        parkTbs.add(parkTb3);
        Map<Long, String> collect = parkTbs.stream().collect(Collectors.toMap(UnionParkTb::getId, UnionParkTb::getName));
        Double collect1 = parkTbs.stream().collect(Collectors.averagingInt(value -> value.getId() > 0 ? 0 : 1));
        System.out.println(collect);
        System.out.println(collect1);
    }
}
