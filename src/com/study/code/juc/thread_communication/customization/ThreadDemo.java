package com.study.code.juc.thread_communication.customization;

/**
 * @ClassName: ThreadDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 17:08
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        ShareResources shareResources = new ShareResources();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResources.print5(i);
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResources.print10(i);
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResources.print15(i);
            }
        }, "C").start();
    }
}
