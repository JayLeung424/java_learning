package com.study.code.design_pattern.patterns.singleton.lazy.type2;

import java.util.Objects;

/**
 * @ClassName: SingletonTest
 * @Description: 懒加载 （线程安全 同步方法）
 * @Author: jiel
 * @Date: 2022/4/1 15:14
 **/
public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("\n【 线程安全 同步方法 】");
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
    }
}

/**
 * 优缺点：
 * 优点： 解决了线程安全的问题
 * 缺点： 效率太低。每个线程在想获得类的实例时候 执行getInstance()方法都要进行同步锁，
 * 同步锁会增加锁竞争，带来系统性能开销，从而导致系统性能下降，因此这种方式也会降低单例模式的性能
 */
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    // 提供一个静态的共有方法, 新增synchronized, 加入同步处理代码， 解决线程安全问题
    public static synchronized Singleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Singleton();
        }
        return instance;
    }
}