package com.study.code.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: InterruptDemo3
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/11 17:45
 **/
public class InterruptDemo4 {
    public static void main(String[] args) {
        // 测试当前线程是否被中断 (检查中断标志) 返回一个boolean并清除中断状态
        // 第二次再调用时中断状态已经被清除， 将返回一个false
        System.out.println(Thread.currentThread().getName() +"\t" +Thread.interrupted());  // false
        System.out.println(Thread.currentThread().getName() +"\t" +Thread.interrupted());  // false

        System.out.println(" ---- 1");
        Thread.currentThread().interrupt();
        System.out.println(" ---- 2");

        System.out.println(Thread.currentThread().getName() +"\t" +Thread.interrupted()); // true
        System.out.println(Thread.currentThread().getName() +"\t" +Thread.interrupted()); // false
    }
}
