package com.study.code.juc.atomics;

import sun.swing.AccumulativeRunnable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassName: AccumulatorCompareDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/11/3 15:59
 **/
public class AccumulatorCompareDemo {
    public static final int _1W = 10000;
    public static final int THREAD_NUMBER = 50;

    public static void main(String[] args) {
        ClickNumber clickNumber = new ClickNumber();
        clickBySynchronized(clickNumber);
        clickByAtomicLong(clickNumber);
        clickByLongAdder(clickNumber);
        clickByAccumulator(clickNumber);
    }

    private static void clickBySynchronized(ClickNumber clickNumber) {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMBER);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickBySynchronized();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime:" + (endTime - startTime) + "毫秒" + "\t clickBySynchronized: " + clickNumber.number);
    }

    private static void clickByAtomicLong(ClickNumber clickNumber) {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMBER);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime:" + (endTime - startTime) + "毫秒" + "\t clickByAtomicLong: " + clickNumber.atomicLong.get());
    }

    private static void clickByLongAdder(ClickNumber clickNumber) {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMBER);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime:" + (endTime - startTime) + "毫秒" + "\t clickByLongAdder: " + clickNumber.longAdder.longValue());
    }

    private static void clickByAccumulator(ClickNumber clickNumber) {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMBER);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByAccumulator();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime:" + (endTime - startTime) + "毫秒" + "\t clickByAccumulator: " + clickNumber.accumulator.get());
    }
}

class ClickNumber {
    int number = 0;

    public synchronized void clickBySynchronized() {
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);
    public void clickByAtomicLong() {
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0);
    public void clickByAccumulator() {
        accumulator.accumulate(1);
    }
}
