package com.study.code.juc.sale_ticket.lock;

/**
 * @ClassName: SaleTicket
 * @Description: 销售票
 * @Author: jay
 * @Date: 2022/6/5 15:15
 **/
public class SaleTicket {
    public static void main(String[] args) {
        // 第一步： 创建资源类， 定义属性和操作方法
        Ticket ticket = new Ticket();
        // 第二步 创建多个线程
        new Thread(() -> {
            // 调用卖票的方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"销售员A").start();
        new Thread(() -> {
            // 调用卖票的方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"销售员B").start();
        new Thread(() -> {
            // 调用卖票的方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"销售员C").start();

    }
}
