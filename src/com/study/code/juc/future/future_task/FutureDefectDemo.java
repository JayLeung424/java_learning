package com.study.code.juc.future.future_task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName: FutureAPIDemo
 * @Description: 一些缺点
 * @Author: jiel
 * @Date: 2022/9/28 17:13
 **/
public class FutureDefectDemo {
    /**
     * 结论 --- Future对结果的获取不是很友好  只能通过轮询或者堵塞的方式获取到任务的结果
     * FutureTask 的一些缺点
     * 1、get容易导致堵塞 一般建议放到程序的后面，
     * 2、如果我不愿意等待很长的时间，我希望过时不侯 可以自动离开
     * 3、isDone 轮询  -- 可以避免 get导致堵塞
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        FutureTask<String> task1 = new FutureTask<>(
                () -> {
                    System.out.println(Thread.currentThread().getName() +"\t" + " ... come in！");
                    try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                    return "task1 over"; }
        );

        Thread t1 = new Thread(task1, "t1");
        t1.start();

        // todo Defect1 get容易导致堵塞 一般建议放到程序的后面, 一旦调用就不见不散,非要等到结果出来才会离开，无论是否计算完成
        System.out.println(task1.get());

        System.out.println(Thread.currentThread().getName() +"\t" + " ... 忙其他任务了！");

        // todo Defect2 如果我不愿意等待很长的时间，我希望过时不侯 可以自动离开
        // 我只等待3s 如果3s内没有返回结果 抛错TimeoutException
        System.out.println(task1.get(3,TimeUnit.SECONDS));

        // todo Defect3 isDone  轮询的方式会耗费CPU资源，而且不见得可以及时拿到计算结果
        // 如果想要异步获取结果 通常都用轮询  的方式获取结果 尽量不堵塞
        while (true){
            if(task1.isDone()){
                System.out.println(task1.get());
                break;
            }else{
                // 暂停毫秒
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("正在处理中, 不要再催啦, 越催越慢, 再催熄火了!");
            }
        }

    }
}
