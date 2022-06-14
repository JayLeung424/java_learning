package com.design.patterns.singleton.eager.type2;

/**
 * @ClassName: SingletonTest
 * @Description: 饿汉式 （静态代码块）
 * @Author: jiel
 * @Date: 2022/4/1 14:52
 **/

public class SingletonTest {
    public static void main(String[] args) {
        // 调用两个getInstance() 获取同一个对象 共享一份内存！
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
    }
}

// 饿汉式
class Singleton {
    // 1. 构造方法被private修饰, 外部是不可以new Singleton();
    private Singleton() {
    }

    // 2. 声明Singleton静态变量
    private static Singleton instance;

    // 3. 静态代码块中进行对象实例
    static {
        instance = new Singleton();
    }

    // 4. 提供外部接口, 返回内部创建的singleton对象实例
    public static Singleton getInstance() {
        return instance;
    }

    ;
}
