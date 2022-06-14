package com.study.code.juc.sale_ticket.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Ticket
 * @Description: 票
 * @Author: jay
 * @Date: 2022/6/5 15:17
 **/
public class Ticket {

    /**
     * 票数
     */
    private int number = 30;

    /**
     * 可重入锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 操作方法 ：卖票
     */
    public void sale() {
        // 上锁
        lock.lock();
        try {
            // 判断是否有票
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + ": 卖出第" + (number--) + "张票, 剩下: " + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}
