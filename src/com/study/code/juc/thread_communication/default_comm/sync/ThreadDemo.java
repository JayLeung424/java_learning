package com.study.code.juc.thread_communication.default_comm.sync;

/**
 * @ClassName: ThreadDemo1
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 16:04
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        // 第一步 和 第二步在Share对象中
        Share share = new Share();
        // 第三步 创建多个线程 调用资源类的操作方法
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.desr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread BB").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread CC").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.desr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread DD").start();
    }
}
