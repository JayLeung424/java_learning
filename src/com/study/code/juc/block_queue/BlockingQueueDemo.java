package com.study.code.juc.block_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: BlockingQueueDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/6/19 14:31
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个阻塞队列 param 长度
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 第一组
        System.out.println(blockingQueue.add("A"));
        System.out.println(blockingQueue.add("B"));
        System.out.println(blockingQueue.add("C"));
        // System.out.println(blockingQueue.add("C"));
        // 检查元素
        System.out.println(blockingQueue.element());

        // 加入第四个元素  报错信息: Queue full(队列满了,无法继续插入)
        // System.out.println(blockingQueue.add("D"));

        // 取元素 先进先出
        System.out.println(blockingQueue.remove()); // A
        System.out.println(blockingQueue.remove()); // B
        System.out.println(blockingQueue.remove()); // C
        System.out.println(blockingQueue.remove()); // C


        // 第二组方法
        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("B"));
        System.out.println(blockingQueue.offer("C"));
        // 第四个 插入返回false  但是没有报错
        System.out.println(blockingQueue.offer("D"));

        // 取值
        System.out.println(blockingQueue.poll()); // A
        System.out.println(blockingQueue.poll()); // B
        System.out.println(blockingQueue.poll()); // C
        // 不报错 返回null
        System.out.println(blockingQueue.poll()); // null


        // 第三组
        blockingQueue.put("A");
        blockingQueue.put("B");
        blockingQueue.put("C");
        // 第四个 第四由于没有位置， 会一直等待 直到有位置然后塞进去
        // blockingQueue.put("D");

        // 取值
        System.out.println(blockingQueue.take()); // A
        System.out.println(blockingQueue.take()); // B
        System.out.println(blockingQueue.take()); // C
        // 由于取不到第四个 会一直等待 阻塞 直到取到值
        // System.out.println(blockingQueue.take());


        // 第四组
        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("B"));
        System.out.println(blockingQueue.offer("C"));
        // 第四个会阻塞 然后设置阻塞的时间
        System.out.println(blockingQueue.offer("D",3L, TimeUnit.SECONDS));


        System.out.println(blockingQueue.poll()); // A
        System.out.println(blockingQueue.poll()); // B
        System.out.println(blockingQueue.poll()); // C
        // 第四个会阻塞 然后设置阻塞的时间
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS)); // null


    }
}
