package com.study.code.juc.thread_pool;

import java.util.concurrent.*;

public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {

        // 一池可扩容线程
        /**
         * @param corePoolSize 线程池的核心线程数
         * @param maximumPoolSize 能容纳的最大线程数
         * @param keepAliveTime 空闲线程存活时间
         * @param unit 存活的时间单位
         * @param workQueue 存放提交但未执行任务的队列
         * @param threadFactory 创建线程的工厂类:可以省略
         * @param handler 等待队列满后的拒绝策略:可以省略
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
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

    /**
     * 可缓存线程池
     *
     * @return
     */
    public static ExecutorService newCachedThreadPool() {
        /**
         * corePoolSize 线程池的核心线程数
         * maximumPoolSize 能容纳的最大线程数
         * keepAliveTime 空闲线程存活时间
         * unit 存活的时间单位
         * workQueue 存放提交但未执行任务的队列
         * threadFactory 创建线程的工厂类:可以省略
         * handler 等待队列满后的拒绝策略:可以省略
         */
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
}
