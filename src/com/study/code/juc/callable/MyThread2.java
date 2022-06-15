package com.study.code.juc.callable;

import java.util.concurrent.Callable;

/**
 * @ClassName: MyThread2
 * @Description: 实现Callable接口
 * @Author: jiel
 * @Date: 2022/6/15 10:43
 **/
class MyThread2 implements Callable {
    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName() + "线程进入了 call 方法 开始准备睡觉");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "睡醒了");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1024;
    }
}