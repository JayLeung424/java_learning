package com.design.patterns.singleton.static_inner;

/**
 * @ClassName: StaticInnerClass
 * @Description: 单例模式 静态内部类
 * @Author: jiel
 * @Date: 2022/4/2 13:12
 **/
public class StaticInnerClass {
}

// 静态内部类完成 推荐使用
// Singleton类加载的时候 不会实例的，只是调用getInstance()方法的时候，才会实例对象
// static final 又保证了线程安全 只有一份 所以是推荐使用的
class Singleton {
    // 构造器私有化
    private Singleton() {
    }

    // 静态内部类 该类有一个静态属性
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    // 提供一个静态共有方法
    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}