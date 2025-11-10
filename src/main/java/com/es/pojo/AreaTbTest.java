//package com.es.pojo;
//
//import com.alibaba.fastjson.JSON;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class AreaTbTest {
//
//    public static void main(String[] args) {
//        AreaTb a1 = new AreaTb(2, 110000, "北京", 1, 2);
//
//        AreaTb a2 = new AreaTb(3, 110100, "北京市", 110000, 3);
//        AreaTb a3 = new AreaTb(4, 110101, "东城区", 110100, 4);
//        AreaTb a4 = new AreaTb(5, 110102, "西城区", 110100, 4);
////----------------------------------------------------------------------------------------------------------
//        AreaTb a5 = new AreaTb(20, 120000, "天津", 1, 2);
//
//        AreaTb a6 = new AreaTb(21, 120100, "天津市", 120000, 3);
//
//        AreaTb a7 = new AreaTb(22, 120101, "和平区", 120100, 4);
//        AreaTb a8 = new AreaTb(23, 120102, "河东区", 120100, 4);
//        AreaTb a9 = new AreaTb(24, 120103, "河西区", 120100, 4);
//
////----------------------------------------------------------------------------------------------------------
//        AreaTb a10 = new AreaTb(39, 130000, "河北省", 1, 2);
//
//        AreaTb a11 = new AreaTb(40, 130100, "石家庄市", 130000, 3);
//
//        AreaTb a12 = new AreaTb(41, 130102, "长安区", 130100, 4);
//        AreaTb a13 = new AreaTb(42, 130103, "桥西区", 130100, 4);
//
//        AreaTb a14 = new AreaTb(63, 130200, "唐山市", 130000, 3);
//
//        AreaTb a15 = new AreaTb(64, 130202, "路南区", 130200, 4);
//        AreaTb a16 = new AreaTb(65, 130203, "路北区", 130200, 4);
//        AreaTb a17 = new AreaTb(66, 130204, "古冶区", 130200, 4);
//
//
//        List<AreaTb> areaTbs = Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17);
//
//        Map<Integer, List<AreaTb>> areaMap = areaTbs.stream().filter(it -> it.getAreaType() != 4).collect(Collectors.groupingBy(AreaTb::getAreaParentId));
//        List<AreaTb> provinces = areaTbs.stream().filter(it -> it.getAreaParentId() == 1).collect(Collectors.toList());
//
//        ArrayList<AreaTbVo> list = new ArrayList<>();
//        for (AreaTb province : provinces) {
//            AreaTbVo vo = new AreaTbVo();
//            vo.setAreaTb(province);
//            List<AreaTbVo> subList = new ArrayList<>();
//            full(vo, areaMap, subList);
//            vo.setSubList(subList);
//            list.add(vo);
//        }
//        System.out.println(JSON.toJSONString(list));
//    }
//
//    public static void full(AreaTbVo vo, Map<Integer, List<AreaTb>> map, List<AreaTbVo> subList) {
//        AreaTb areaTb = vo.getAreaTb();
//        List<AreaTb> areaTbs = map.get(areaTb.getAreaCode());
//        if (areaTbs != null && !areaTbs.isEmpty()) {
//            for (AreaTb tb : areaTbs) {
//                AreaTbVo areaTbVo = new AreaTbVo();
//                areaTbVo.setAreaTb(tb);
//                List<AreaTbVo> sub = new ArrayList<>();
//                subList.add(areaTbVo);
//                full(areaTbVo, map, sub);
//                areaTbVo.setSubList(sub);
//            }
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
