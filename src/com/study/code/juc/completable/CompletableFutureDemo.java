package com.study.code.juc.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: CompletableFutureDEmo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/22 10:41
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 异步调用  没有返回值
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "voidCompletableFuture ");
        });
        voidCompletableFuture.get();


        // 异步调用  有返回值
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "integerCompletableFuture ");
            // 模拟异常
            int i = 1/0;
            return 1024;
        });
        integerCompletableFuture.whenComplete((t,u)->{
            System.out.println("----t:" + t);       // 方法的返回值
            System.out.println("----u:" + u);       // 异常的信息
        });
    }
}
