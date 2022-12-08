package com.study.code.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: VolatileNoAutomicDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/26 11:34
 **/
class MyNumber {
    // int number ;
    volatile int number;

    // public synch ronized void addPlusPlus() {
    public void addPlusPlus() {
        number++;
    }
}

public class VolatileNoAtomicDemo {
    /**
     * 不保证原子性
     *
     */
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(myNumber.number);
    }

}
