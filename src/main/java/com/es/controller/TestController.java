package com.es.controller;

import com.es.ip.NetworkUtil;
import com.es.pojo.AreaTb;
import com.es.pojo.ParkTokenTb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/my")
@Slf4j
public class TestController {
//
//    @Autowired
//    private RedissonClient redissonClient;

    private static List<AreaTb> areaTbs = new ArrayList<>();

    @GetMapping("/oom")
    public String ip(@RequestParam(value = "n", required = false) Integer n, HttpServletRequest request) {
        try {
            String ipAddress = NetworkUtil.getIpAddress(request);
            System.out.println(ipAddress);
            String desc = "IP: "+ipAddress;
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                int anInt = random.nextInt(10);
                if (anInt % 2 == 0) {
                    AreaTb areaTb = new AreaTb();
                    areaTb.setAreaCode(anInt);
                    areaTb.setAreaName("城市编码:" + anInt);
                    log.info("creat areaTb>>>" + areaTb);
                    areaTbs.add(areaTb);
                } else {
                    ParkTokenTb tokenTb = new ParkTokenTb();
                    log.info("creat tokenTb>>>" + tokenTb);
                }
            }
            log.info("areaTbs size: {}", areaTbs.size());
            return desc;
        } catch (Exception e) {
            log.error("sds", e);
        }
        return null;
    }

    @RequestMapping("/lock")
    public String test() {
        String result = null;
//        RLock lock = redissonClient.getLock("sss");
        try {
//            lock.lock();
            result = "...................加锁成功";
            //业务处理
        } catch (Exception e) {
            e.printStackTrace();
            result = "...................加锁异常";
        } finally {
//            lock.unlock();
        }
        return result;
    }

    @RequestMapping("/")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        long time = System.currentTimeMillis() / 1000;
        return "现在时间：" + time + ", hello test";
    }

    @RequestMapping("/_doc")
    public void _doc(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = "E:\\bolink\\20210427_一码付商户接入\\☆http接入：先看说明！！！！！.txt";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        pw.flush();
        pw.write(String.valueOf(builder));
        pw.close();
        reader.close();
    }

    @RequestMapping("/hua")
    public String natapp(HttpServletRequest request, HttpServletResponse response) {
        String remoteAddr = request.getRemoteAddr();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "画雕，下午好，当前时间：" + sdf.format(new Date()) +
                "\r\n当前ip地址：" + remoteAddr;
    }

    @RequestMapping("/jsChina")
    public String jsChina(HttpServletRequest request, HttpServletResponse response) {
        long time = System.currentTimeMillis() / 1000;
        return "现在时间：" + time + ", hello jsChina";
    }

}
