package com.study.code.juc.thread_daemon;

/**
 * @ClassName: Main
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 15:01
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        /**
         * 用户线程:平时用到的普通线程,自定义线程
         * 守护线程:运行在后台,是一种特殊的线程,比如垃圾回收
         * 当主线程结束后,用户线程还在运行,JVM 存活
         * 如果没有用户线程,都是守护线程,JVM 结束
         */
        Thread aa = new Thread(() -> {
            // isDaemon 是否是守护线程
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "AA");
        // 设置为守护线程
        aa.setDaemon(true);
        aa.start();
        System.out.println(Thread.currentThread().getName() + " over");
    }
}
