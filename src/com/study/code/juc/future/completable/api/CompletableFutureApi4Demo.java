package com.study.code.juc.future.completable.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CompletableFutureApi4Demo
 * @Description:
 * @Author: jiel
 * @Date: 2022/9/29 16:13
 **/
public class CompletableFutureApi4Demo {
    public static void main(String[] args) {
        /**
         * 谁更快？
         */

        CompletableFuture<String> playerA = CompletableFuture.supplyAsync(() -> {
            System.out.println("playerA come in ... ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "player A";
        });
        CompletableFuture<String> playerB = CompletableFuture.supplyAsync(() -> {
            System.out.println("playerB come in ... ");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "player B";
        });

        CompletableFuture<String> result = playerA.applyToEither(playerB, f -> {
            return f + " is winner";
        });

        System.out.println(Thread.currentThread().getName() + "\t" + "----" + result.join());
    }
}
