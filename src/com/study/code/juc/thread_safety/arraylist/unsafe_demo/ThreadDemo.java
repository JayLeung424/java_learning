package com.study.code.juc.thread_safety.arraylist.unsafe_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: ThreadDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 18:39
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        // 创建ArrayList集合
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 往集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));
                // 从集合中获取内容
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        /**
         * 错误信息
         * java.lang.ArrayIndexOutOfBoundsException: 16
         * 	at java.util.ArrayList.add(ArrayList.java:463)
         * 	at com.study.code.juc.thread_safety.arraylist.unsafe_demo.ThreadDemo.lambda$main$0(ThreadDemo.java:20)
         * 	at java.lang.Thread.run(Thread.java:748)
         */
    }
}
