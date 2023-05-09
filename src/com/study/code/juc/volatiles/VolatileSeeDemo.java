package com.study.code.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: VolatileseeDemo
 * @Description: Volatile可见性
 * @Author: jiel
 * @Date: 2022/10/26 10:43
 **/
public class VolatileSeeDemo {
    // static boolean flag = true;
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        /**
         * 可见性:
         * 1、不使用volatile
         *  结论  主线程的flag修改过了(false)， 但是t1线程的还是最开始的true,
         * 2、使用volatile
         *  结论 保证不同线程对某个变量完成操作后结果及时可见， 即该共享变量一旦改变，所有线程立即可见
         *
         *
         *  线程t1 为什么看不到主线程的flag修改为false的flag值呢？
         *  问题可能：
         *  1、主线程修改flag之后没有将其刷新到主内存, 所以t1线程看不到
         *  2、主线程将flag刷新到主内存，但是t1一直读取的是自己工作内存的flag值， 没有去主内存更新获取flag的最新的值
         *
         *  我们的诉求:
         *  1、线程中修改了自己工作内存中的副本之后，立刻将其刷新到主内存
         *  2、工作内存中每次读取共享变量时候，都去主内存重新读取，然后拷贝到工作内存
         *
         *  解决:
         *  使用volatile修饰共享变量,就可以达到上面的效果, 被volatile修改的变量有以下特点:
         *  1、线程中读取的时候,每次读取都回去主内存中读取共享变量最新的值, 然后将其复制到工作内存
         *  2、线程中修改了工作内存中变量的副本, 修改之后会立即刷新到主内存中
         *
         */
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t  ---- come in ");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + "\t ---- Flag 被设置为false, 线程结束");
        }, "t1").start();


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // main 主线程2s改为false  希望t1 可以立刻间听到
        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t 修改完成 flag:" + flag);
    }

}
