package com.study.code.juc.future.future_task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: CallableDemo
 * @Description: 比较Runnable 和 Callable
 * @Author: jiel
 * @Date: 2022/6/15 10:38
 **/
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Runnable方式创建线程
        new Thread(new MyRunnableThread(), "AA").start();


        // Callable方式创建线程
        // new Thread(new MyThread2(),"AA").start();  错误写法，Thread构造方法没有Callable
        // 解决方法: 找一个类 即和Runnable有关系 又和 Callable有关系  然后进行转换
        // 目标: FutureTask
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new MyCallableThread());
        // lambda表达式
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(() -> 1024);
        new Thread(futureTask1, "Callable线程1").start();
        new Thread(futureTask2, "Callable线程2").start();

        for (int i = 0; i < 10; i++) {

            // get()  获取返回值
            Integer result1 = futureTask1.get();

            System.out.println("futureTask1 获取内容" + result1); }
    }
}