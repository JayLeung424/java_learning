package com.study.code.juc.sale_ticket.sync;

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
     * 操作方法 ：卖票
     */
    public synchronized void sale() {
        // 判断是否有票
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + ": 卖出第" + (number--) + "张票, 剩下: " + number);
        }
    }
}
