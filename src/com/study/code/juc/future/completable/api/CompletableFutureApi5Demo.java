package com.study.code.juc.future.completable.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CompletableFutureApi5Demo
 * @Description:
 * @Author: jiel
 * @Date: 2022/9/29 16:42
 **/
public class CompletableFutureApi5Demo {

    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -- 启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });


        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -- 启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Object> result = future1.thenCombine(future2, (x, y) -> {
            System.out.println("开始结果合并, X:" + x + " Y:" + y + " = " + (x + y));
            return x+y;
        });
        System.out.println(result.join());
    }
}
