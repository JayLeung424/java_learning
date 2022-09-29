package com.study.code.juc.future.completable;

import java.util.concurrent.*;

/**
 * @ClassName: CompletableFutureDEmo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/22 10:41
 **/
public class CompletableFutureConstructDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //  开启线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 构造方法1 - 异步调用  没有返回值
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "voidCompletableFuture1 ");
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        });
        System.out.println("runAsync 返回:" + voidCompletableFuture1.get());

        // 构造方法2 - 异步调用  没有返回值
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "voidCompletableFuture2 ");
        }, threadPool);
        System.out.println("runAsync 返回:" + voidCompletableFuture2.get());


        // 构造方法3 - 异步调用  有返回值
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "integerCompletableFuture ");
            return 1024;
        });
        System.out.println(integerCompletableFuture1.get());

        // 构造方法3 - 异步调用  有返回值
        CompletableFuture<Integer> integerCompletableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "integerCompletableFuture ");
            return 1024;
        },threadPool);
        System.out.println(integerCompletableFuture1.get());
    }
}
