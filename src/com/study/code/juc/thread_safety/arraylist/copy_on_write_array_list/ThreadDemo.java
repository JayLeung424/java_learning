package com.study.code.juc.thread_safety.arraylist.copy_on_write_array_list;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName: ThreadDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/6/5 18:51
 **/
public class ThreadDemo {
    /**
     * 写时复制技术
     * 独立写（拷贝一份） 、 读取新的集合
     * 复制思想:
     * 当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行 Copy，
     * 复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。
     *
     * @param args
     */
    public static void main(String[] args) {
        // 推荐使用的！ juc包下的
        List<String> list = new CopyOnWriteArrayList();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 往集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));
                // 从集合中获取内容
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /**
         * CopyOnWriteArrayList add()方法的源码
         *    public boolean add(E e) {
         *         final ReentrantLock lock = this.lock;
         *         lock.lock();
         *         try {
         *             Object[] elements = getArray();
         *             int len = elements.length;
         *             Object[] newElements = Arrays.copyOf(elements, len + 1);
         *             newElements[len] = e;
         *             setArray(newElements);
         *             return true;
         *         } finally {
         *             lock.unlock();
         *         }
         *     }
         */
    }
}
