package com.study.code.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: InterruptDemo3
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/11 17:45
 **/
public class InterruptDemo3 {
    public static void main(String[] args) {
        //  实例方法 interrupt() 仅仅是设置线程的中断状态位 设置为 true, 不会停止线程
        Thread t1 = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() +"\t 中断标志位:"+Thread.currentThread().isInterrupted() +" 程序停止");
                    break;
                }
                try {
                    // 如果该线程阻塞的调用了 wait() sleep() join() 的方法, 那么中断状态将被清除，并且将收到InterruptedException
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // 所以在catch代码块中重新将中断状态设置为true
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }

                System.out.println(" ---- hello InterruptDemo3");
            }
        }, "t1");
        t1.start();

        // 暂停毫秒
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        //
        t1.interrupt();
        new Thread(()->{t1.interrupt();},"t2").start();
    }
}
