package com.study.code.juc.future.completable;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @ClassName: CompletableFutureUseDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/9/29 10:45
 **/
public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 使用自定义线程池避免 主线程关闭 CompletableFuture 默认使用的线程池会被关闭的问题
        ExecutorService threadPool = Executors.newFixedThreadPool(3);


        // i++的效果
        try {

            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + " --- come in!");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" --- 1s 以后出结果: " + result);
                // 制造异常
                int i = 10 / 0;
                return result;
            }).whenComplete((value, exception) -> {
                /**
                 * @params value     -- 执行以后的结果
                 * @params exception -- 执行过程中的异常信息
                 */
                if (Objects.isNull(exception)) {
                    System.out.println(" --- 计算完成， 更新系统UpdateVa: " + value);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常信息：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName() + "线程先去忙其他的任务了!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        //   --- 切记 主线程不要立刻结束 否则 CompletableFuture 默认使用的线程池会被关闭
        // try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    /**
     * 结论 - Future可以做的  CompletableFuture都可以做到
     */
    private static void future1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " --- come in!");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" --- 1s 以后出结果: " + result);
            return result;
        });

        // --- 主线程
        System.out.println(Thread.currentThread().getName() + "线程先去忙其他的任务了!");
        System.out.println("completableFuture result : " + completableFuture.get());
    }
}
