//package com.es.netty;
//
//import org.elasticsearch.common.network.InetAddresses;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.math.BigDecimal;
//import java.net.Socket;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class YushiTest {
//    private Object sentLcok = new Object();
//    private static Socket socket = null;
//
//    public static void main(String[] args) throws Exception {
//        double cappappaymoney = 0.0d;
//        if (true) {
//            cappappaymoney = new BigDecimal("0.03000").doubleValue();
//        }
//        System.out.println("cappappaymoney = " + cappappaymoney);
//    }
//    public static boolean isJson(String msg) {
//        int count = 0;
//        boolean flag = false;
//        for (int i = 0; i < msg.length(); i++) {
//            if (msg.charAt(i) == '{') {
//                count++;
//            }
//            if (msg.charAt(i) == '}') {
//                count--;
//            }
//        }
//        if (count == 0) flag = true;
//        return flag;
//    }
//    public static  boolean sendMsg(String msg) throws Exception {
//        socket = new Socket("127.0.0.1",6796);
//        OutputStream out = null;
//        boolean flag = true;
//        try {
//            byte[] data = msg.getBytes("GBK");
//            out = socket.getOutputStream();
//            out.write(data, 0, data.length);
//            out.flush();
//        } catch (Exception e) {
//            flag = false;
//        } finally {
//            try {
//                if (out != null) {
//                    out.flush();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return flag;
//    }
//}
