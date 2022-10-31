package com.study.code.juc.cas;

/**
 * @ClassName: UseCaseBeforeDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/31 14:38
 **/
public class UseCASBeforeDemo {
    /**
     * 使用cas之前 多线程的i++ 场景
     */
    volatile int number = 0;

    public int getNumber() {
        return number;
    }

    /**
     * 要使用 synchronized重量级锁
     */
    public synchronized void setNumber() {
        this.number++;
    }
}
