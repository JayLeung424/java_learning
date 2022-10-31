package com.study.code.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: SpinLockDemo
 * @Description: 实现一个自旋锁 , 复习CAS思想
 * 自旋锁的好处: 循环比较获取没有类似wait的阻塞
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法 自己持有锁5s
 * B随后进来发现当前线程持有锁， 只能通过自旋去等待， 直到A释放锁后B随后抢到
 * @Author: jiel
 * @Date: 2022/10/31 17:07
 **/
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t" + "come in ...");
        // 首次进来 期望当前没有线程占用，然后把自己写进去
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        // 自己想出来了  那么期望值就是自己，然后把null写入
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + " ---- task over, unlock ....");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.lock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.unLock();
        },"A").start();

        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            spinLockDemo.lock();
            spinLockDemo.unLock();
        },"B").start();
    }
}
