package com.es.excute;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.*;

public class Demo {

    private RedissonClient redissonClient;

    public void test() {
        RLock lock = redissonClient.getLock("sss");
        try {
            lock.lock();
            //业务处理
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    public static void main1(String[] args) throws InterruptedException {
        int i = 4>>1; //100 0010 2
        System.out.println(i);
        int b = 7<<1; //111 1110 3
        System.out.println(b);
        System.out.println(4&7);
        System.out.println(4|7);
        System.out.println(4^7);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));
        poolExecutor.execute(new DemoRunnable());
    }

}
