package com.study.code.juc.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @ClassName: ForkJoinDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/22 10:09
 **/
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建拆分任务的对象
        MyTask myTask = new MyTask(0, 100);
        // 创建分支 合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        // 获取最终合并的结果
        Integer result = forkJoinTask.get();

        // 关闭池对象
        forkJoinPool.shutdown();
    }
}
