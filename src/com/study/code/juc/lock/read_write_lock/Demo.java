package com.study.code.juc.lock.read_write_lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: Demo
 * @Description: 演示读写锁降级
 * @Author: jiel
 * @Date: 2022/6/17 13:32
 **/
public class Demo {
    public static void main(String[] args) {
        // 可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        // 读锁
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        // 写锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();



        // 锁降级(写锁降级为读锁) 获取写锁、获取读锁、释放写锁 释放读锁
        // 获取写锁
        writeLock.lock();
        System.out.println("jay study");

        // 获取读锁
        readLock.lock();
        System.out.println(" -- read");

        // 释放写锁
        writeLock.unlock();

        // 释放读锁
        readLock.unlock();
    }
}
