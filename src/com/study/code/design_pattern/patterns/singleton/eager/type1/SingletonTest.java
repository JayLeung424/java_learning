package com.design.patterns.singleton.eager.type1;

/**
 * @ClassName: SingletonTest1
 * @Description: 饿汉式 静态常量
 * @Author: jiel
 * @Date: 2022/4/1 14:33
 **/
public class SingletonTest {
    public static void main(String[] args) {
        // 调用两个getInstance() 获取同一个对象 共享一份内存！
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
    }
}

// 饿汉式
// 特点: 不管饿不饿,都要提前准备好.
// 饿汉式，就像它的名字，饥不择食，定义的时候直接初始化。
// 因为instance是个静态变量，所以它会在类加载的时候完成实例化，不存在线程安全的问题。
// 这种方式不是懒加载，不管我们的程序会不会用到，它都会在程序启动之初进行初始化。
class Singleton {
    // 1. 构造方法被private修饰, 外部是不可以new Singleton();
    private Singleton() {
    }

    // 2. 本类内部创建对象实例
    private final static Singleton instance = new Singleton();

    // 3. 提供外部接口, 返回内部创建的singleton对象实例
    public static Singleton getInstance() {
        return instance;
    }

    ;
}
