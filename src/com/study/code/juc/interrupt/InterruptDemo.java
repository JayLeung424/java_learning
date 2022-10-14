package com.study.code.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName: InterruptDemo
 * @Description:  使用中断标识停止线程
 * @Author: jiel
 * @Date: 2022/10/11 11:36
 **/
public class InterruptDemo {

    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {

        m2_atomicBoolean();
    }

    private static void m3_interrupt_api() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted被修改为true,程序停止");
                    break;
                }
                System.out.println(" ---- hello interrupt api");
            }
        }, "t1");
        t1.start();

        try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) {e.printStackTrace(); }

        // t2向t1发出协商， 将t1的中断标志位设置为true 希望t1停止
        /*new Thread(() -> {
            // interrupt() 发起一个协商 但是不会立刻停止线程
            t1.interrupt();
        }, "t2").start();*/

        // t1自己设置
        t1.interrupt();
    }

    private static void m2_atomicBoolean(){
        new Thread(()->{
            while (true){
                if (atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true,程序停止");
                    break;
                }
                System.out.println(" ---- hello atomicBoolean");
            }
        },"t1").start();

        try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void m1_volatile(){
        new Thread(()->{
            while (true){
                if (isStop){
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true,程序停止");
                    break;
                }
                System.out.println(" ---- hello volatile");
            }
        },"t1").start();

        try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}

