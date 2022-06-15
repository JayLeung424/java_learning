package com.study.code.juc.thread_safety.arraylist.vector_demo;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @ClassName: ThreadDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 18:44
 **/
public class ThreadDemo {
    // Vector 源码  add方法被 synchronized 修饰
    // public synchronized boolean add(E e) {
    //     modCount++;
    //     ensureCapacityHelper(elementCount + 1);
    //     elementData[elementCount++] = e;
    //     return true;
    // }
    public static void main(String[] args) {
        List<String> list = new Vector<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 往集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));
                // 从集合中获取内容
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
