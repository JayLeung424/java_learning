package com.study.code.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CASDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/31 15:02
 **/
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // 认为没有人动过  还是5  如果还是5 那么就修改为2022
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t " + atomicInteger.get());
        // 被修改过了 和预期不一样 那么就重试或者放弃
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t " + atomicInteger.get());
    }
}
