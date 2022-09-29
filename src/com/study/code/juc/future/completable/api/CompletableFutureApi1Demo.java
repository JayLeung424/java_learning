package com.study.code.juc.future.completable.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CompletableFutureApiDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/9/29 15:04
 **/
public class CompletableFutureApi1Demo {
    public static void main(String[] args) {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        // System.out.println(completableFuture.join());
        // System.out.println(completableFuture.join());
        // 没有计算完成的情况下，给我一个替代结果
        // 立即获取结果不阻塞 计算完，返回计算完成后的结果  没算完，返回设定的valueIfAbsent值
        System.out.println(completableFuture.getNow("默认值"));
        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.join());
    }
}
