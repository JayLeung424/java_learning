package com.study.code.design_pattern.patterns.singleton.lazy.type1;

import java.util.Objects;

/**
 * @ClassName: LazySingletonTest
 * @Description: 懒汉式 （线程不安全）
 * @Author: jiel
 * @Date: 2022/4/1 14:58
 **/
public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("\n【 线程不安全 】");
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
    }
}

/**
 * 优缺点：
 * 优点： 起到了Lazy Loading的效果 但是只可以单线程使用
 * 缺点： 多线程情况下，多个线程同时进入if判断语句块，满足判断条件，进而产生多个实例。
 * 切记： 多线程情况下不要使用
 */
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    // 提供一个静态的共有方法, 当使用该方法的时候 才去创建instance
    public static Singleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Singleton();
        }
        return instance;
    }
}