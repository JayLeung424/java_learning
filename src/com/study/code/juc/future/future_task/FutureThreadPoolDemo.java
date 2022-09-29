package com.study.code.juc.future.future_task;

import java.util.concurrent.*;

/**
 * @ClassName: FutureThreadPoolDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/9/28 15:43
 **/
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 三个任务 目前 开启多个异步线程 来处理 请问耗时多久？
        long startTime = System.currentTimeMillis();

        // 开启线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> task1 = new FutureTask<>(
                () -> { try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                    return "task1 over"; }
        );
        threadPool.submit(task1);

        FutureTask<String> task2 = new FutureTask<>(
                () -> { try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
                    return "task2 over"; }
        );
        threadPool.submit(task2);

        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        // ----- CostTime:362毫秒
        System.out.println("----- CostTime:"+(System.currentTimeMillis() - startTime)+"毫秒");

        // get 以后  时间 ----- CostTime:563毫秒
        task1.get();
        task2.get();

        System.out.println("----- CostTime:"+(System.currentTimeMillis() - startTime)+"毫秒");

        threadPool.shutdown();
    }

    private static void m1(){

        // 三个任务 目前只有一个main来处理 请问耗时多久？
        long startTime = System.currentTimeMillis();
        // 暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        try { TimeUnit.MILLISECONDS.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); }

        long endTime = System.currentTimeMillis();

        System.out.println("----- CostTime:"+(endTime - startTime)+"毫秒");
        System.out.println(Thread.currentThread().getName() +"\t" + " ----- END");
    }
}
