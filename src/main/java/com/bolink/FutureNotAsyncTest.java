package com.bolink;

import java.util.List;
import java.util.concurrent.*;

public class FutureNotAsyncTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> "abc_", executor)
                .thenApplyAsync(param -> {
                    System.out.println("--1--" + Thread.currentThread());
                    return param.toUpperCase();
                }, executor)
                .thenComposeAsync(f -> {
                    System.out.println("--2--" + Thread.currentThread());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        //ignore
                    }
                    return CompletableFuture.supplyAsync(() -> f + "456_", executor);
                }, executor).thenComposeAsync(f -> {
                    System.out.println("--3--" + Thread.currentThread());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        //ignore
                    }
                    return CompletableFuture.supplyAsync(() -> f + "789", executor);
                }, executor)
                .thenAcceptAsync(System.out::println, executor);

        voidCompletableFuture.join();
        executor.shutdown();

        boolean terminated = executor.awaitTermination(1, TimeUnit.SECONDS);
        if (!terminated) {
            List<Runnable> remainTaskList = executor.shutdownNow();
            if (!remainTaskList.isEmpty()) {
                for (Runnable runnable : remainTaskList) {
                    runnable.run();
                }
            }
        }
    }
}