package com.study.code.juc.lock.stamped_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @ClassName: StampedLockDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/11/16 14:30
 **/
public class StampedLockDemo {

    static int number = 37;

    static StampedLock stampedLock = new StampedLock();

    public  void write(int num){
        // 加一个写锁, 返回一个戳记
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "\t 写线程准备修改!");
        try {
            number = num + 13;
        }finally {
            // 释放写锁 需要同一个戳记
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName() + "\t 写线程修改完毕!");
    }

    /**
     * 悲观读  当读没有完成的时候 写锁无法获得锁
     */
    public void read(){
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "\t come in readLock code block, 4s continue !");
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 正在读取中 !");
        }
        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "\t 读取完毕, 结果为: " + result);
            System.out.println("写线程没有修改成功, 读锁时候写锁无法介入, 传统的读写互斥!");
        }finally {
            stampedLock.unlockRead(stamp);
        }
    }


    /**
     * 乐观读  当读没有完成的时候 写锁可以获得锁
     */
    public void  tryOptimisticRead(){
        // 获取一个乐观读的戳记
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        System.out.println(Thread.currentThread().getName() + "\t come in tryOptimisticRead code block, 4s continue !");
        // 睡眠4s中 很乐观的认为读取中没有其他的线程修改number值
        System.out.println("4s 前stampedLock.validate(stamp) true无修改,false有修改 = " + stampedLock.validate(stamp));
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 正在读取中 !" + i + "s 后 stampedLock.validate(stamp) = " + stampedLock.validate(stamp));
        }
        // 证明有人改过了 乐观读失败 升级为悲观读
        if (!stampedLock.validate(stamp)) {
            try {
                System.out.println("有人修改过 ---- 有写的操作");
                stamp = stampedLock.readLock();
                System.out.println("从乐观读 升级为 悲观读");
                result = number;
                System.out.println("重新悲观读后的result :" + result);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
    }

    public static void main(String[] args) {
        StampedLockDemo resource = new StampedLockDemo();

        new Thread(() -> {
            // resource.read();
            resource.tryOptimisticRead();
        }, "readThread").start();
        // 2s 情况  有介入
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {throw new RuntimeException(e);}
        // 6s 情况 没有介入
        // try {TimeUnit.SECONDS.sleep(6);} catch (InterruptedException e) {throw new RuntimeException(e);}

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +"\t come in write code block!");
            resource.write(100);
        }, "writeThread").start();
    }
}
