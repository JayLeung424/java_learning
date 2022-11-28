package com.study.code.juc.atomics;

import com.sun.deploy.util.StringUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: AutomicIntegerDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/11/3 10:33
 **/
public class AtomicIntegerDemo {
    public static final int size = 50;

    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= size; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }
        // main	  result:49000
        // 使用了原子类 为什么结果还不对呢？
        // 因为主线程跑完了， 但是子线程累加还没有运行完
        System.out.println(Thread.currentThread().getName() + "\t  result:" + myNumber.atomicInteger.get());

        // 引入countDownLach
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                } finally {
                    // -1个
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        try {
            // 等待所有的线程都结束了
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "\t  result:" + myNumber.atomicInteger.get());
    }
}

class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}