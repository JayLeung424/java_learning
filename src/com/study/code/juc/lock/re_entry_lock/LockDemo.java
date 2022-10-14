package com.study.code.juc.lock.re_entry_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/14 11:23
 **/
public class LockDemo {
    public static void main(String[] args) {
        // Lock 演示可重入锁
        Lock lock = new ReentrantLock();

        // create thread
        new Thread(() -> {
            try {
                // 上锁
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " - come in 外层调用");
                try {
                    // 上锁
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " - come in 内层调用");
                } finally {
                    // 如果这个不释放 无所谓，这个线程依旧正常执行
                    // 但是 new thread一直无法消费(不影响自己, 把别人坑了)  因为thread1加锁和释放次数不一样， 第二个线程无法获取到锁，导致一直在等待
                    // lock.unlock();  // 正常情况 加锁几次 解锁几次
                }
            } finally {
                // 释放
                lock.unlock();
            }
        }, "thread1").start();

        // create new thread
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " - come in ");
            }finally {
                lock.unlock();
            }
        },"thread2").start();
    }
}
