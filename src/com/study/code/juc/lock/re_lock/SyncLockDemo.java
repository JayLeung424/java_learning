package com.study.code.juc.lock.re_lock;

/**
 * @ClassName: SyncLockDemo
 * @Description: 可重入锁
 * @Author: jiel
 * @Date: 2022/6/14 11:16
 **/
public class SyncLockDemo {
    public synchronized void add() {
        add();
    }

    public static void main(String[] args) {
        // 可重入锁也叫递归锁
        new SyncLockDemo().add();

        // synchronized
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " - 外层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + " - 中层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + " - 内层");
                    }
                }
            }
        }, "thread1").start();
    }
}
