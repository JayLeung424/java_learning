package com.study.code.juc.thread_communication.default_comm.sync;

/**
 * @ClassName: Share
 * @Description: 资源类
 * 第一步 创建资源类 定义属性和操作方法
 * @Author: jay
 * @Date: 2022/6/5 16:05
 **/
public class Share {
    // 初始值
    private int num = 0;

    /**
     * +1的操作方法
     */
    public synchronized void incr() throws InterruptedException {
        // 第二步  判断、干活、通知

        // +1的操作是 num = 0时候
        // 判断: numb是否为0 如果不是0 等待
        // if (num != 0) { // 由于wait可能会存在虚假唤醒（调用了唤醒方法 实际上没有唤醒）  所以要放到while中
        while (num != 0) {
            this.wait();
        }
        // 干活: 如果值是0 就++
        num++;
        System.out.println(Thread.currentThread().getName() + " :: " + num);
        // 通知: 其他的所有线程
        this.notifyAll();
    }

    /**
     * -1的操作方法
     */
    public synchronized void desr() throws InterruptedException {
        // 第二步  判断、干活、通知

        // -1的操作是 num = 1时候
        // 判断: numb是否为1 如果不是1 等待
        while (num != 1) {
            this.wait();
        }
        // 干活: 如果值是1 就--
        num--;
        System.out.println(Thread.currentThread().getName() + " :: " + num);
        // 通知: 其他的所有线程
        this.notifyAll();
    }
}

