package com.study.code.juc.future.completable.api;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName: CompletableFutureApi3Demo
 * @Description:
 * @Author: jiel
 * @Date: 2022/9/29 15:33
 **/
public class CompletableFutureApi3Demo {

    public static void main(String[] args) {
/*        // 没有return
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenApply(f -> {
            return f + 3;
        }).thenAccept(System.out::println);*/

        // 任务A执行完 执行任务B, B不需要A的结果
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());
        // 任务A执行完 执行任务B, B需要A的结果, 但是任务B没有返回结果
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(System.out::println).join());
        // 任务A执行完 执行任务B, B需要A的结果, 同时任务B有返回值
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + " + resultB").join());
    }
}
