package com.study.code.juc.lock.re_lock;

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
                System.out.println(Thread.currentThread().getName() + " - 外层");
                try {
                    // 上锁
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " - 内层");
                } finally {
                    // 释放
                    // 如果这个不释放 无所谓，这个线程依旧正常执行
                    // 但是 new thread一直无法消费(不影响自己, 把别人坑了)
                    // lock.unlock();
                }
            } finally {
                // 释放
                lock.unlock();
            }
        }, "thread1").start();

        // create new thread
        new Thread(() -> {
            lock.lock();
            System.out.println("aaa");
            lock.unlock();
        }).start();
    }
}
