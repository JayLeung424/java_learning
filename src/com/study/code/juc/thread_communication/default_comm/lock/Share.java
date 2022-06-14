package com.study.code.juc.thread_communication.default_comm.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Share
 * @Description: 资源类
 * 第一步 创建资源类 定义属性和操作方法
 * @Author: jay
 * @Date: 2022/6/5 16:05
 **/
public class Share {

    /**
     * 初始值
     */
    private int num = 0;
    /**
     * 创建lock
     */
    private final Lock lock = new ReentrantLock();
    /**
     * 创建condition
     */
    private final Condition condition = lock.newCondition();

    /**
     * +1的操作方法
     */
    public void incr() throws InterruptedException {
        // 第二步  判断、干活、通知
        // 上锁
        lock.lock();
        try {
            // 判断: numb是否为0 如果不是0 等待
            // 由于wait可能会存在虚假唤醒（调用了唤醒方法 实际上没有唤醒）  所以要放到while中
            while (num != 0) {
                condition.await();
            }
            // 干活: 如果值是0 就++
            num++;
            System.out.println(Thread.currentThread().getName() + " :: " + num);

            // 通知: 其他的所有线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    /**
     * -1的操作方法
     */
    public void desr() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断: numb是否为1 如果不是1 等待
            // 由于wait可能会存在虚假唤醒（调用了唤醒方法 实际上没有唤醒）  所以要放到while中
            while (num != 1) {
                condition.await();
            }
            // 干活: 如果值是1 就--
            num--;
            System.out.println(Thread.currentThread().getName() + " :: " + num);

            // 通知: 其他的所有线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}

