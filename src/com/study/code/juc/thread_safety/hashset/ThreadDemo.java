package com.study.code.juc.thread_safety.hashset;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName: ThreadDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 19:02
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        // 演示hashSet
        // Set hashSet = new HashSet<>();

        // 使用juc中的
        Set hashSet = new CopyOnWriteArraySet();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 往集合中添加内容
                hashSet.add(UUID.randomUUID().toString().substring(0, 8));
                // 从集合中获取内容
                System.out.println(hashSet);
            }, String.valueOf(i)).start();
        }
        /** 报错信息
         * java.util.ConcurrentModificationException
         * 	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1442)
         * 	at java.util.HashMap$KeyIterator.next(HashMap.java:1466)
         * 	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
         * 	at java.lang.String.valueOf(String.java:2994)
         * 	at java.io.PrintStream.println(PrintStream.java:821)
         * 	at com.study.code.juc.thread_safety.hashset.ThreadDemo.lambda$main$0(ThreadDemo.java:22)
         * 	at java.lang.Thread.run(Thread.java:748)
         */
    }
}
