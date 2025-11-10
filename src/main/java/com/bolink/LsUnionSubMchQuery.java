package com.bolink;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blsign.util.NamedThreadFactory;
import com.bolink.pojo.LsUnionMchIdDTO;
import com.es.lsapp.HttpClientUtil;
import com.es.lsapp.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
public class LsUnionSubMchQuery {

    // 限频配置：40次/20秒 = 2次/秒
    private static final int RATE_LIMIT_PER_20S = 40;
    private static final int RATE_LIMIT_WINDOW_MS = 20000; // 20秒窗口
    private static final long MIN_INTERVAL_MS = RATE_LIMIT_WINDOW_MS / RATE_LIMIT_PER_20S; // 每次请求最小间隔

    // 线程池配置 - 根据限频调整
    private static final int CORE_POOL_SIZE = 1; // 核心线程数，避免过多并发
    private static final int MAX_POOL_SIZE = 1;  // 最大线程数
    private static final int QUEUE_CAPACITY = 500; // 队列容量
    private static final long KEEP_ALIVE_TIME = 60L; // 线程空闲时间

    // 分批处理配置
    private static final int BATCH_SIZE = 500; // 每批处理数量，减少批次大小
    private static final int MAX_RETRY_TIMES = 3; // 最大重试次数

    // 进度监控
    private static final AtomicInteger processedCount = new AtomicInteger(0);
    private static final AtomicInteger successCount = new AtomicInteger(0);
    private static final AtomicInteger errorCount = new AtomicInteger(0);
    private static final AtomicLong startTime = new AtomicLong(0);
    private static final long KEEP_ALIVE = 60L;
    // 限频控制
    private static final Queue<Long> requestTimestamps = new ConcurrentLinkedQueue<>();
    private static final Object rateLimitLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        String readPath = "/Users/xugan/Desktop/564家深圳活跃车场.xls";
//        String writePath = "/Users/xugan/Desktop/省市区乐刷查询11.xlsx";

        List<LsUnionMchIdDTO> list = EasyExcel.read(readPath)
                .head(LsUnionMchIdDTO.class)
                .sheet().doReadSync();

        System.out.println("总共需要处理 " + list.size() + " 个车场");
        String collect = list.stream().map(it -> "'" + it.getParkLot() + "'").collect(Collectors.joining(","));
        System.out.println(collect);
    }

    public static void main1(String[] args) throws InterruptedException {
        String readPath = "/Users/xugan/Desktop/省市区乐刷查询.xlsx";
//        String writePath = "/Users/xugan/Desktop/省市区乐刷查询11.xlsx";

        List<LsUnionMchIdDTO> list = EasyExcel.read(readPath)
                .head(LsUnionMchIdDTO.class)
                .sheet().doReadSync();

        System.out.println("总共需要处理 " + list.size() + " 个车场");

        // 统计需要处理的车场数量
        long emptyCount = list.stream()
                .filter(item -> StrUtil.isEmpty(item.getUnionMchId()))
                .count();
        System.out.println("其中 UnionMchId 为空的： " + emptyCount + " 个");

        if (emptyCount == 0) {
            System.out.println("所有车场都已处理完成，无需再次执行！");
            return;
        }

        // 记录开始时间
        startTime.set(System.currentTimeMillis());

        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_CAPACITY),
                new NamedThreadFactory("freeze-recharge"),
                new ThreadPoolExecutor.CallerRunsPolicy()); // 改为CallerRunsPolicy

        // 创建计数器
        AtomicInteger processedCount = new AtomicInteger(0);
        AtomicInteger successCount = new AtomicInteger(0);

        try {
            // 遍历所有车场，只处理 UnionMchId 为空的
            List<Future<?>> futures = new ArrayList<>();
            int totalProcessed = 0;

            for (int i = 0; i < list.size(); i++) {
                LsUnionMchIdDTO mchIdDTO = list.get(i);
                Thread.sleep(200);
                try {
                    String lsMchId = mchIdDTO.getLsMchId();
                    String unionMchId = queryMchId(lsMchId);
                    if (StrUtil.isNotEmpty(unionMchId)) {
                        mchIdDTO.setUnionMchId(unionMchId);
                        successCount.incrementAndGet();
                    } else {
                        System.out.println("未获取..." + lsMchId);
                    }
                } catch (Exception e) {
                    log.error("处理车场 {} 时发生异常: {}", mchIdDTO.getLsMchId(), e.getMessage());
                } finally {
                    int current = processedCount.incrementAndGet();
                    System.out.println("已处理: " + current + "/" + emptyCount + ", 成功: " + successCount.get());
                }
//                if (StrUtil.isEmpty(mchIdDTO.getUnionMchId())) {
//                    // 只处理空值的车场
//                    Future<?> future = threadPoolExecutor.submit(() -> {
//
//                    });
//                    futures.add(future);
//                    totalProcessed++;
//
//                    // 限制单次执行的数量，避免一次处理太多
//                    if (totalProcessed >= 6000) {
//                        System.out.println("已达到单次处理上限1000个，本次执行结束");
//                        break;
//                    }
//                }
            }

//            if (futures.isEmpty()) {
//                System.out.println("没有找到需要处理的车场");
//                return;
//            }

            System.out.println("本次将处理 " + totalProcessed + " 个车场，等待执行完成...");

            // 等待所有任务完成
//            for (Future<?> future : futures) {
//                try {
//                    future.get();
//                } catch (Exception e) {
//                    log.error("任务执行异常", e);
//                }
//            }

            System.out.println("所有任务执行完成！");

        } finally {
            // 关闭线程池
            System.out.println("开始关闭线程池...");
            threadPoolExecutor.shutdown();

            // 等待线程池完全关闭，增加等待时间
            if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("线程池关闭超时，强制关闭...");
                threadPoolExecutor.shutdownNow();

                // 再次等待强制关闭完成
                if (!threadPoolExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
                    System.err.println("警告：线程池强制关闭后仍有线程未结束");
                }
            }
            System.out.println("线程池已关闭");
        }

        // 确保所有任务都完成后再执行write
        System.out.println("开始导出Excel...");
        try {
            EasyExcel.write(readPath, LsUnionMchIdDTO.class)
                    .sheet("Sheet1")
                    .doWrite(list);
            System.out.println("Excel导出成功！");
        } catch (Exception e) {
            System.err.println("Excel导出失败：" + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("处理完成！成功：" + successCount.get() + "，总计：" + processedCount.get());

        // 统计剩余需要处理的车场
        long remainingCount = list.stream()
                .filter(item -> StrUtil.isEmpty(item.getUnionMchId()))
                .count();
        System.out.println("剩余需要处理的车场： " + remainingCount + " 个");

        if (remainingCount > 0) {
            System.out.println("可以再次运行程序处理剩余车场");
        } else {
            System.out.println("所有车场都已处理完成！");
        }

        System.out.println("程序即将退出...");
    }

    /**
     * 优化后的查询方法
     */
    private static String queryMchId(String lsMchId) {
        String unionMchId = "";

        JSONObject reqJson = new JSONObject();
        reqJson.put("merchantId", lsMchId);
        reqJson.put("subMchType", 4);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "2.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", reqJson.toString());

        String sign = Base64Utils.encodeToString(
                MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8")
                        .toLowerCase().getBytes()
        );
        requestMap.put("sign", sign);

        try {
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/submch/query", requestMap, 3000, 3000);
            System.out.println(result);
            if (StrUtil.isNotEmpty(result)) {
                JSONObject obj = JSON.parseObject(result);
                JSONObject data = obj.getJSONObject("data");
                if (data != null) {
                    // 优化：使用统一的提取逻辑
                    unionMchId = extractSubMchId(data, "unionscan");
                    if (StrUtil.isEmpty(unionMchId)) {
                        unionMchId = extractSubMchId(data, "cups");
                    }
                    if (StrUtil.isEmpty(unionMchId)) {
                        unionMchId = extractSubMchId(data, "unionpayQRA");
                    }
                    if (StrUtil.isEmpty(unionMchId)) {
                        unionMchId = "未获取";
                    }
                }
            }
        } catch (Exception e) {
            log.error("银联商户查询异常, lsMchId: {} {}", lsMchId, e.getMessage());
        }
        return unionMchId;
    }

    /**
     * 提取子商户ID的通用方法
     */
    private static String extractSubMchId(JSONObject data, String arrayKey) {
        JSONArray array = data.getJSONArray(arrayKey);
        if (array != null && !array.isEmpty()) {
            for (int i = 0; i < array.size(); i++) {
                JSONObject item = array.getJSONObject(i);
                String subMchId = item.getString("subMchId");
                if (StrUtil.isNotEmpty(subMchId)) {
                    return subMchId;
                }
            }
        }
        return "";
    }
}
