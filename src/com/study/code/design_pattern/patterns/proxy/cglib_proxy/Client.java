package com.study.code.design_pattern.patterns.proxy.cglib_proxy;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 14:52
 **/
public class Client {
    public static void main(String[] args) {
        // 创建目标对象
        TeacherDao target = new TeacherDao();
        // 获取代理对象， 并将目标对象传递给代理对象
        TeacherDao proxyInstance = (TeacherDao) new ProxyFactory(target).getProxyInstance();
        // 执行代理对象的方法 会触发 intecept 方法 从而实现对目标对象的调用
        proxyInstance.teach();
    }
}
