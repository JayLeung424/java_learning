package com.study.code.juc.lock.fair_lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Ticket
 * @Description: 票  --- 公平锁 和 非公平锁
 * @Author: jay
 * @Date: 2022/6/5 15:17
 **/
public class Ticket {

    /**
     * 票数
     */
    private int number = 30;

    /**
     * 可重入锁    new ReentrantLock(bool)
     * 公平锁 和 非公平锁
     * public ReentrantLock(boolean fair) {
     * sync = fair ? new FairSync() : new NonfairSync();
     * }
     */
    private final ReentrantLock lock = new ReentrantLock(true);

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
