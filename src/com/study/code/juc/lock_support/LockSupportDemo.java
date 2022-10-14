package com.study.code.juc.lock_support;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockSupportDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/13 10:33
 **/
public class LockSupportDemo {

    public static void main(String[] args) {
        /**
         * 没有锁块的要求
         * 先唤醒 再等待 同样支持
         * 牢记 成双成对 使用即可
         */
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t  -- come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t  -- 被唤醒");
        }, "t1");
        t1.start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t  -- 发出通知");
        }, "t2").start();
    }

    private static void syncAwaitSignal() {
        // syncWaitNotify();
        /**
         * await 和 signal 也要在lock 和 unlock 之中才可以使用
         * 先signal 后 await 线程无法被唤醒
         */
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t  -- come in");
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "\t  -- 被唤醒");
        },"t1").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t  -- 发出通知");
            }finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void syncWaitNotify() {
        // synchronized
        /**
         * wait() notify() 的使用
         * 1、wait 和 notify 必须放在 同步块或者同步方法(synchronized)中
         * 2、一定要先wait 再notify  否则线程一直等待
         */
        Object objectLock = new Object();
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t  -- come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t  -- 被唤醒");
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t  -- 发出通知");
            }
        }, "t2").start();
    }
}
