package com.study.code.juc.thread_communication.customization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ShareResources
 * @Description: 共享资源
 * 第一步 创建资源类
 * @Author: jay
 * @Date: 2022/6/5 16:59
 **/
public class ShareResources {
    /**
     * 定义标志位
     * A = 1、B = 2、C = 3
     */
    private Integer flag = 1;

    /**
     * 创建lock锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 创建三个condition
     */
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    /**
     * 资源类中的操作方法
     * 打印5次
     *
     * @param loop 第几轮
     */
    public void print5(int loop) {
        // 上锁
        lock.lock();
        try {
            // 判断 使用while是为了避免虚假唤醒
            while (flag != 1) {
                // 等待
                c1.await();
            }
            // 打印5次
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :: 当前值" + i + " 第" + loop + "轮");
            }
            // 修改标志位
            flag = 2;
            // 唤醒B （打印10次）
            c2.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 资源类中的操作方法
     * 打印10次
     *
     * @param loop 第几轮
     */
    public void print10(int loop) {
        // 上锁
        lock.lock();
        try {
            // 判断 使用while是为了避免虚假唤醒
            while (flag != 2) {
                // 等待
                c2.await();
            }
            // 打印5次
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " :: 当前值" + i + " 第" + loop + "轮");
            }
            // 修改标志位
            flag = 3;
            // 唤醒C （打印15次）
            c3.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 资源类中的操作方法
     * 打印15次
     *
     * @param loop 第几轮
     */
    public void print15(int loop) {
        // 上锁
        lock.lock();
        try {
            // 判断 使用while是为了避免虚假唤醒
            while (flag != 3) {
                // 等待
                c3.await();
            }
            // 打印5次
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " :: 当前值" + i + " 第" + loop + "轮");
            }
            // 修改标志位
            flag = 1;
            // 唤醒A （打印10次）
            c1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}
