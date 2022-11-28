package com.study.code.juc.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @ClassName: AtomicIntegerFieldUpdaterDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/11/3 11:40
 **/
public class AtomicIntegerFieldUpdaterDemo {
    /**
     * 以一种线程安全的方式操作非线程安全对象的某些字段
     * 需求：
     * 10个线程
     * 每个线程转账1000元
     * 不使用synchronized 尝试使用AtomicIntegerFieldUpdater
     */
    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {

                    for (int j = 0; j < 1000; j++) {
                        // bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(bankAccount.money);
    }
}

// 资源类
class BankAccount {
    String bankName = "CCB";
    /**
     * 钱数
     */
    // 第一步 更新对象的属性 必须使用public volatile
    public volatile int money = 0;

    // public synchronized void add() {
    public void add() {
        money++;
    }

    // 第二步 因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法newUpdater()创建一个更新器，并且需要设置想要更新的类和属性。
    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    // 不加 synchronized 保证高性能原子性 局部微创小手术
    public void transMoney(BankAccount bankAccount){
        fieldUpdater.getAndIncrement(bankAccount);
    }
}