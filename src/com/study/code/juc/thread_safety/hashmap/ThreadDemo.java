package com.study.code.juc.thread_safety.hashmap;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ThreadDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 19:07
 **/
public class ThreadDemo {
    public static void main(String[] args) {
        // 演示hashMap
        // Map<String, Object> hashMap = new HashMap<>();
        Map<String, Object> hashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                // 往集合中添加内容
                hashMap.put(key,UUID.randomUUID().toString().substring(0, 8));
                // 从集合中获取内容
                System.out.println(hashMap);
            }, String.valueOf(i)).start();
        }

    }
}
