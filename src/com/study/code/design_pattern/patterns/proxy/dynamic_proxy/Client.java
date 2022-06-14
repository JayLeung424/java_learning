package com.design.patterns.proxy.dynamic_proxy;

/**
 * @ClassName: Client
 * @Description: Jayce
 * @Author: jayce
 * @Date: 2022/4/12 10:29
 **/
public class Client {

    public static void main(String[] args) {
        // 创建目标对象 - 被代理对象
        ITeacherDao teacherDao = new TeacherDao();
        // com.design.patterns.proxy.dynamic_proxy.TeacherDao@5cad8086
        System.out.println(teacherDao);

        // 给目标对象 创建代理对象 可以转成ITeacherDao
        // 不转TeacherDao原因是: TeacherDao是被代理对象, 自己没必要代理自己
        ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(teacherDao).getProxyInstance();

        // class com.sun.proxy.$Proxy0  可以看出 内存中动态生成了代理对象
        System.out.println("proxyInstance = " + proxyInstance.getClass());
        // com.design.patterns.proxy.dynamic_proxy.TeacherDao@5cad8086 地址和被代理对象地址一样
        System.out.println("proxyInstance = " + proxyInstance);

        // 通过代理对象，调用目标对象的方法
        proxyInstance.teach();
        proxyInstance.sayHello("tom");
    }
}
