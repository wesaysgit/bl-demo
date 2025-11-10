package com.es.pojo;

import cn.hutool.core.util.RandomUtil;

import java.util.Random;

public class DtoTest {

    public static void main(String[] args) {
        Random random = new Random();
        int type = random.nextInt(3);
        if (type == 0) type += 1;
        System.out.println(type);
        BaseDto baseDto = buildDto(type);
        if (baseDto instanceof SubDto1) {
            SubDto1 baseDto1 = (SubDto1) baseDto;
            System.out.println(baseDto1.getUserId());
        }
        if (baseDto instanceof  SubDto2) {
            System.out.println(((SubDto2) baseDto).getUserName());
        }
        System.out.println(baseDto);
    }

    public static BaseDto buildDto(int type) {
        BaseDto baseDto = null;
        if (type == 1) {
            SubDto1 subDto1 = new SubDto1();
            subDto1.setId(1L);
            subDto1.setUserId(12122L);
            baseDto = subDto1;
        }
        if (type == 2) {
            SubDto2 subDto2 = new SubDto2();
            subDto2.setId(1L);
            subDto2.setUserName("lili");
            baseDto = subDto2;
        }
        return baseDto;
    }
}
