package com.bolink;

import com.bolink.pojo.PaySenceNoEnum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoSenceTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(36,17,16,15,14,13,12,6,5,4,2,1,0);
        Set<Integer> nosenceList = new HashSet<>();
        if(list != null && list.size() > 0){
            for (Integer no:
                    list) {
                if(no == null){
                    continue;
                }
                if(no == PaySenceNoEnum.WXNOSENCE.senceNo || no == PaySenceNoEnum.ALINOSENCE.senceNo || no == PaySenceNoEnum.UNIONNOSENCE.senceNo
                        || no == PaySenceNoEnum.JHNOSENCE.senceNo || no == PaySenceNoEnum.ICBCNOSENCE.senceNo || no == PaySenceNoEnum.CMBNOSENCE.senceNo
                        || no == PaySenceNoEnum.CSDNNOSENCE.senceNo || no == PaySenceNoEnum.UNIONMSNOSENCE.senceNo || no == PaySenceNoEnum.ABCAUTOPAY.senceNo
                        || no == PaySenceNoEnum.ICBCV1NOSENCE.senceNo || no == PaySenceNoEnum.GUIZHOUNOSENCE.senceNo || no == PaySenceNoEnum.BOCJRNOSENCE.senceNo
                        || no == PaySenceNoEnum.CJNSNOSENCE.senceNo || no == PaySenceNoEnum.YICHANGNOSENCE.senceNo || no == PaySenceNoEnum.MOBONOSENCE.senceNo
                        || no == PaySenceNoEnum.GSNXNOSENCE.senceNo || no == PaySenceNoEnum.JTZHTCNOSENCE.senceNo || no == PaySenceNoEnum.CIXINOSENCE.senceNo
                        || no == PaySenceNoEnum.GZYRNOSENCE.senceNo || no == PaySenceNoEnum.ENSHINOSENCE.senceNo || no == PaySenceNoEnum.KUNSHANZHNOSENCE.senceNo
                        || no == PaySenceNoEnum.SCRCUNOSENCE.senceNo
                        || no == PaySenceNoEnum.SHIJIAZHUANGJTZHNOSENCE.senceNo|| no == PaySenceNoEnum.WUXINOSENCE.senceNo|| no == PaySenceNoEnum.XINWUNOSENCE.senceNo
                        || no == PaySenceNoEnum.WUZHONGNOSENCE.senceNo||no == PaySenceNoEnum.HUISHANNOSENCE.senceNo|| no == PaySenceNoEnum.LIANGXINOSENCE.senceNo
                        || no == PaySenceNoEnum.ZHIBONANTONGNOSENCE.senceNo|| no == PaySenceNoEnum.WXXSQ.senceNo ||no == PaySenceNoEnum.WUHANNOSENCE.senceNo
                        ||no == PaySenceNoEnum.ZHENJIANGNOSENCE.senceNo){
                    nosenceList.add(no);
                }
            }
        }
        System.out.println(nosenceList);
    }
}
