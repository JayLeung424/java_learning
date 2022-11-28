package com.study.code.juc.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @ClassName: AtomicIntegerArrayDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/11/3 10:44
 **/
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {

        // 不声明初始值 只有长度
        // AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);
        // AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);

        // 声明初始值 只有长度
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 2, 3, 4, 5});


        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }

        int temp = 0;

        // 设置  获取0下标的值(1)  并设置为2002
        temp = atomicIntegerArray.getAndSet(0,2002);
        System.out.println(temp +"\t" +atomicIntegerArray.get(0));
        // 自增
        temp = atomicIntegerArray.getAndIncrement(0);
        System.out.println(temp +"\t" +atomicIntegerArray.get(0));
    }
}
