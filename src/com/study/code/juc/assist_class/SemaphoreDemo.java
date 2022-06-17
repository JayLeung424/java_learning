package com.study.code.juc.assist_class;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SemaphoreDemo
 * @Description: 信号灯 Semaphore
 * @Author: jiel
 * @Date: 2022/6/16 15:56
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 案例: 抢车位 6部汽车  3个停车位

        // 定义停车位
        Semaphore semaphore = new Semaphore(3);

        // 模拟6辆汽车
        for (int i = 1; i <= 6; i++) {
            // 停车
            new Thread(()->{
                try {
                    // 抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "号汽车 抢到了车位!!");
                    // 随机一段时间后 车开走了
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName() + "号汽车 - 离开车位");
                }catch (Exception e){

                }finally {
                    // 释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
