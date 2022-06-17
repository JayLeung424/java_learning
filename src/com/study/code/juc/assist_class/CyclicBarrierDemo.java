package com.study.code.juc.assist_class;

import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName: CyclicBarrierDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/16 15:08
 **/
public class CyclicBarrierDemo {
    /**
     * 定义神龙召唤需要的龙珠总数
     */
    private final static int NUMBER = 7;

    public static void main(String[] args) {
        // 案例:集齐 7 颗龙珠就可以召唤神龙

        // 定义循环栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(
                NUMBER, () -> {
            System.out.println("集齐7颗龙珠就可以召唤神龙!!!");
        });

        // 集齐7颗龙珠的过程
        for (int i = 1; i <= NUMBER; i++) {
            new Thread(() -> {
                try {

                    System.out.println(Thread.currentThread().getName() + "星龙珠被收集到了!");
                    // 等待
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
