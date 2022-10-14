package com.study.code.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: InterruptDemo2
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/11 16:37
 **/
public class InterruptDemo2 {

    public static void main(String[] args) {
        //  实例方法 interrupt() 仅仅是设置线程的中断状态位 设置为 true, 不会停止线程

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println("-----:" + i);
            }
            System.out.println("t1线程调用 interrupt() 后的中断标识02: " + Thread.currentThread().isInterrupted()); // true
        }, "t1");
        t1.start();

        System.out.println("t1线程默认的中断标识: " + t1.isInterrupted()); // false

        // 暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        t1.interrupt(); // true

        System.out.println("t1线程调用 interrupt() 后的中断标识01: " + t1.isInterrupted()); // true

        try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        // 不活动的线程 不会收到影响  2000毫秒以后 t1已经结束了
        System.out.println("t1线程调用 interrupt() 后的中断标识03: " + t1.isInterrupted()); // false
    }
}
