package com.study.code.juc.thread_safety.arraylist.collections_demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: ThreadDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 18:47
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        // Collections.synchronizedList() 定义一个线程安全的集合
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                // 往集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                // 从集合中获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
