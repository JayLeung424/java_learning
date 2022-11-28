package com.study.code.juc.atomics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @ClassName: AtomicReferenceFieldUpdaterDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/11/3 14:00
 **/
public class AtomicReferenceFieldUpdaterDemo {
    /**
     * 需求：
     * 多线程并发调用一个类的初始化方法，如果没有初始化过，将执行初始化工作
     * 要求只能初始化一次，只有一个线程操作成功
     */
    public static void main(String[] args) {
        MyVar myVar = new MyVar();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                myVar.ini(myVar);
            }, String.valueOf(i)).start();
        }
    }
}

class MyVar {
    public volatile Boolean isInit = Boolean.FALSE;
    AtomicReferenceFieldUpdater fieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "isInit");

    public void ini(MyVar myVar) {
        if (fieldUpdater.compareAndSet(myVar, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + "\t" + "------- start init, need 2s");
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {throw new RuntimeException(e);}
            System.out.println(Thread.currentThread().getName() + "\t" + "------- over init");
        }else {
            System.out.println(Thread.currentThread().getName() +"\t" + "已经有其他的线程在进行初始化工作了!");
        }
    }
}
