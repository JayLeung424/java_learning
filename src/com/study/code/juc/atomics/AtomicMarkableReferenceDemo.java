package com.study.code.juc.atomics;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @ClassName: AtomicMarkableReference
 * @Description:
 * @Author: jay
 * @Date: 2022/11/3 10:56
 **/
public class AtomicMarkableReferenceDemo {

    /**
     * 默认100 false 表示没有人动过
     */
    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) throws InterruptedException {

        // 解决是否修改过的问题

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t" + "默认标示: " + marked);
            // 暂停1s 等待后面的t2线程 和 t1拿到一样的flag标示，都是false
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
            /**
             * @param 100 期望值
             * @param 1000 想改成的值
             * @param 期望的修改标示
             * @param 想改成的修改标示
             */
            markableReference.compareAndSet(100, 1000, marked, !marked);
        }, "t1").start();
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t" + "默认标示: " + marked);
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {throw new RuntimeException(e);}
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            // false  修改失败 被线程1修改过了
            // markableReference.isMarked()); 为true 被改了
            System.out.println(Thread.currentThread().getName() + "\t" + "t2线程CAS result:" + b);
            System.out.println(Thread.currentThread().getName() + "\t" + markableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t" + markableReference.getReference());

        }, "t2").start();
    }
}
