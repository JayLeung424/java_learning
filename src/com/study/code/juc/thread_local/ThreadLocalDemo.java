package com.study.code.juc.thread_local;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadLocalDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/11/9 14:18
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) {
        // 需求1: 5个销售卖房子, 集团高层只关心销售总量的精准统计数
        // 需求2: 5个销售卖完随机的房子, 各自独立销售的额度, 自己业绩按照提成走, 分灶吃饭，各个销售自己动手 丰衣足食
        House house = new House();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                // System.out.println(size);
                for (int j = 0; j < size; j++) {
                    try {
                        house.saleHouse();
                        house.saleVolumeByThreadLocal();
                    }finally {
                        // 切记 : 线程池的场景下，线程经常会被复用，
                        // 如果不清理自定义的Thread变量，可能会影响后续的内务逻辑和内存泄漏等问题
                        house.saleVolume.remove();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "号销售, 销售了 " + house.saleVolume.get() + " 套");
            }, String.valueOf(i)).start();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + "\t  总共卖出: " + house.saleCount + " 套房子");
    }
}

class House {
    int saleCount = 0;

    public synchronized void saleHouse() {
        saleCount++;
    }

    /**
     * 如何正确使用ThreadLocal?
     */
    // ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
    //     @Override
    //     protected Integer initialValue(){
    //         return 0;
    //     }
    // };
    // Jdk8 以后使用这种方式
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);

    public void saleVolumeByThreadLocal() {
        saleVolume.set(1 + saleVolume.get());
    }
}