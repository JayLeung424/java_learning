package com.study.code.juc.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: ThreadLocalDemo2
 * @Description:
 * @Author: jay
 * @Date: 2022/11/9 14:52
 **/
public class ThreadLocalDemo2 {

    public static void main(String[] args) {
        /**
         * 【强制】: 必须回收自定义的Thread变量，尤其是线程池的场景下，线程经常会被复用，
         * 如果不清理自定义的Thread变量，可能会影响后续的内务逻辑和内存泄漏等问题，尽量使用try finally代码块进行回收
         */

        MyDate myDate = new MyDate();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer beforeInt = myDate.threadLocalField.get();
                        myDate.add();
                        Integer afterInt = myDate.threadLocalField.get();
                        System.out.println(Thread.currentThread().getName() + " before: " + beforeInt + "  after:" + afterInt);
                    } finally {
                        // 核心逻辑代码
                        myDate.threadLocalField.remove();
                    }
                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}

class MyDate {
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocalField.set(threadLocalField.get() + 1);
    }
}
