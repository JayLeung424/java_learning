package com.study.code.juc.fork_join;

import java.util.concurrent.RecursiveTask;

/**
 * @ClassName: MyTask
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/22 10:09
 **/
public class MyTask extends RecursiveTask<Integer> {
    /**
     * 拆分差值 不能超过10  计算10以内的运算
     */
    private static final Integer VALUE = 10;

    /**
     * 拆分的开始值
     */
    private int begin;

    /**
     * 拆分的结束值
     */
    private int end;

    /**
     * 返回结果
     */
    private int result;

    /**
     * 创建有参构造
     *
     * @param begin 拆分开始值
     * @param end   拆分结束值
     */
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // 判断两个差值是否大于10
        if ((end - begin) <= VALUE) {
            // 相加的操作
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            // 进一步拆分
            // 获取到数据的中间值
            int mid = (begin + end) / 2;
            // 拆分左边
            MyTask leftTask = new MyTask(begin,mid);
            leftTask.fork();
            // 拆分右边
            MyTask rightTask = new MyTask(mid,end);
            rightTask.fork();

            result = leftTask.join() + rightTask.join();
        }
        return result;
    }
}
