package com.study.code.juc.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: NewSingleThreadDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/21 17:53
 **/
public class NewSingleThreadPoolDemo {
    public static void main(String[] args) {

        // 一池一线程
        /**
         * @param corePoolSize 线程池的核心线程数
         * @param maximumPoolSize 能容纳的最大线程数
         * @param keepAliveTime 空闲线程存活时间
         * @param unit 存活的时间单位
         * @param workQueue 存放提交但未执行任务的队列
         * @param threadFactory 创建线程的工厂类:可以省略
         * @param handler 等待队列满后的拒绝策略:可以省略
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            // 10个顾客
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 顾客在办理手续");
                });
            }
        } catch (Exception e) {
        } finally {
            // 关闭线程
            executorService.shutdown();
        }
    }
}
