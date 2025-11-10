package com.es.wesays;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cdl {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        //代表十张图片
        int size = 10;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        ArrayList<Integer> integers = new ArrayList<>();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            int finalI = i;
            service.execute(() -> {
                //TODO 业务逻辑处理
                int n = test(finalI);
                integers.add(n);
                // countDown就是这个子线程执行完后 里边的state会-1
                countDownLatch.countDown();
            });
        }
        System.out.println("countDownLatch = " + countDownLatch);
        try {
            // 等待所有子线程业务执行完毕后才会执行这里代码
            // 也就是等待state=0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("所有任务执行完毕"+(end-start));
        System.out.println("integers = " + integers);
    }

    public static int test(int finalI) {
        System.out.println("任务"+finalI+"执行开始>>>>>>>>>>");
        Random random = new Random();
        int i = random.nextInt(3000);
        try {
            System.out.println("random = " + i);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()+"任务"+finalI+"执行结束>>>>>>>>>>");
        return i;
    }

}
