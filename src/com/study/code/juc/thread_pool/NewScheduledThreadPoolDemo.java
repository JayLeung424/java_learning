package com.study.code.juc.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: NewScheduledThreadPoolDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/22 11:03
 **/
public class NewScheduledThreadPoolDemo {
    public static void main(String[] args) {
        // 线程池支持定时以及周期性执行任务
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        ScheduledFuture<?> schedule = executorService.schedule(() -> {
            System.out.println(Thread.currentThread().getName());
        }, 1L, TimeUnit.SECONDS);

    }
}
