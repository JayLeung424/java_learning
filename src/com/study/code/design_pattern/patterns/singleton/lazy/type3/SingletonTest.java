package com.study.code.design_pattern.patterns.singleton.lazy.type3;

import java.util.Objects;

/**
 * @ClassName: SingletonTest
 * @Description: 懒加载 双重检查
 * @Author: jiel
 * @Date: 2022/4/1 15:46
 **/
public class SingletonTest {
}

/**
 * 优缺点：
 * 优点： 解决了线程安全的问题
 * 缺点： 效率太低。每个线程在想获得类的实例时候 执行getInstance()方法都要进行同步。
 * 实际上这个方法只需要执行一次实例化代码就够了, 后面想获得该类实例直接return就好了
 */
class Singleton {
    // volatile 内存可见化
    // volatile 防止重排序 因为对象的实例化和instance赋值(地址)有时间差
    // 多线程主要围绕可见性和原子性两个特性而展开，使用volatile关键字修饰的变量，保证了其在多线程之间的可见性，即每次读取到volatile变量，一定是最新的数据
    private static volatile Singleton instance;

    private Singleton() {
    }

    // 提供一个静态的共有方法, 新增synchronized, 加入同步处理代码， 解决线程安全问题
    public static Singleton getInstance() {
        if (Objects.isNull(instance)) {
            // 可以保证只有一个线程在这里执行
            synchronized (Singleton.class) {
                if (Objects.isNull(instance)) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}