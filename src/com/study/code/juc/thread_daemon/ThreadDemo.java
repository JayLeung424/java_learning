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
         * 守护线程:运行在后台,是一种特殊的线程（为其他的线程服务的）,比如垃圾回收
         *
         * 总结 : 当主线程结束后,用户线程还在运行,JVM 存活
         * 如果没有用户线程,都是守护线程,JVM 结束
         */
        Thread aa = new Thread(() -> {
            // isDaemon 是否是守护线程
            System.out.println(Thread.currentThread().getName() + "::" + (Thread.currentThread().isDaemon() ? "用户线程" :"守护线程"));
            while (true) {

            }
        }, "AA");
        //
        /**
         * daemon --  设置为守护线程
         * setDaemon(true) 必须在 start() 方法执行之前设置 否则会报 IllegalThreadStateException 异常
         *
         */
        aa.setDaemon(true);
        aa.start();
        System.out.println(Thread.currentThread().getName() + " over");
    }
}
