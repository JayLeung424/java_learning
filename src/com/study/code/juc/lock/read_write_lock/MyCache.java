package com.study.code.juc.lock.read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: MyCache
 * @Description: 资源类
 * @Author: jiel
 * @Date: 2022/6/17 11:07
 **/
public class MyCache {

    /**
     * 创建map集合
     */
    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 创建读写锁对象
     */
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * 存储数据
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        // 写数据前 +写锁
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "正在做写的操作！");
            // 暂停一会儿
            TimeUnit.MICROSECONDS.sleep(300);
            // 放数据
            map.put(key, value);

            System.out.println(Thread.currentThread().getName() + " 写完了 " + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 写完了以后 解锁
            rwLock.writeLock().unlock();
        }
    }


    /**
     * 取数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        // 读锁 +锁
        rwLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在做读取的操作！");

            // 暂停一会儿
            TimeUnit.MICROSECONDS.sleep(300);
            // 放数据
            result = map.get(key);

            System.out.println(Thread.currentThread().getName() + " 取完了 " + key);
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 读锁解锁
            rwLock.readLock().unlock();
        }
        return result;
    }
}
