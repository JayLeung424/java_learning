package com.study.code.juc.lock.re_entry_lock;

/**
 * @ClassName: ReEntryLockDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/10 16:06
 **/
public class ReEntryLockDemo {

    /**
     * 指的是可重复可递归调用的锁, 在外层使用锁以后，在内部依然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
     * 结果打印：
     * t1	 ---- come in
     * t1	 ---- come in
     * t1	 ---- come in
     * t1	 ---- m1 end
     */
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "\t ---- come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t ---- m1 end");
    }
    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName() + "\t ---- come in");
        m3();
    }
    public synchronized void m3(){
        System.out.println(Thread.currentThread().getName() + "\t ---- come in");
    }

    public static void main(String[] args) {
        /**
         * 可重入代码块
         * thread1 - 外层
         * thread1 - 中层
         * thread1 - 内层
         */
        final Object o = new Object();
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


        /**
         * 可重入方法
         * 结果打印：
         * t1	 ---- come in
         * t1	 ---- come in
         * t1	 ---- come in
         * t1	 ---- m1 end
         */
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
        new Thread(
                ()->{ reEntryLockDemo.m1();}
        ,"t1").start();
    }

}
