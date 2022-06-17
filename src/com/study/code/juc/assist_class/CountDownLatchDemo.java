package com.study.code.juc.assist_class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/16 14:58
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 6 个同学陆续离开教室后值班同学才可以关门。
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<String>());
        // 创建 CountDownLatch 对象， 设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "号同学离开了教室");
                } catch (Exception e) {
                } finally {
                    // 用于子任务告知当前任务的完成
                    // AQS::state -= 1 子任务完成后释放锁
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        //主线程 await 休息
        System.out.println("主线程睡觉");
        try {
            // if(AQS::state == 0) unpark 跳出等待
            // 用于等待子任务的完成
            countDownLatch.await();
            //全部离开后自动唤醒主线程
            System.out.println("全部离开了,现在的计数器为" + countDownLatch.getCount());

        } catch (InterruptedException e) {
        }
    }
}
