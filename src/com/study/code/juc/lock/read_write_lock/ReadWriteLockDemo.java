package com.study.code.juc.lock.read_write_lock;

/**
 * @ClassName: ReadWriteLockDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/6/17 11:06
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        // 创意一个资源类
        MyCache myCache = new MyCache();


        // 创建 往里面放数据
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put("Str" + num, num);
            }, String.valueOf(i)).start();
        }
        // 创建 往里面放数据
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get("Str" + num);
            }, String.valueOf(i)).start();
        }
    }
}
