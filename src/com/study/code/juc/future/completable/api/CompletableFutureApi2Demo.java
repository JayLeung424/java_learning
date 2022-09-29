package com.study.code.juc.future.completable.api;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CompletableFutureApi2Demo
 * @Description:
 * @Author: jiel
 * @Date: 2022/9/29 15:23
 **/
public class CompletableFutureApi2Demo {
    public static void main(String[] args) {
        /**
         * handle  上一步的计算结果 可以传递给下一步使用
         * 出现异常  带着异常走下去
         */
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1;
        }).handle((f,e) -> {
            int i = 1/0;
            System.out.println("222");
            return f + 2;
        }).handle((f,e) -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (Objects.isNull(e)) {
                System.out.println("计算结果 : " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() +" 去进行其他的任务了... ");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void thenApply(){
        /**
         * thenApply  上一步的计算结果 可以传递给下一步使用
         * 计算结果存在依赖关系 如果有一步出现了问题 戛然而止
         */
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1;
        }).thenApply(f -> {
            System.out.println("222");
            return f + 2;
        }).thenApply(f -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (Objects.isNull(e)) {
                System.out.println("计算结果 : " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() +" 去进行其他的任务了... ");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
