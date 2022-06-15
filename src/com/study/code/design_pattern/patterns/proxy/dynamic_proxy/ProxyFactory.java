package com.study.code.design_pattern.patterns.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: TeacherDaoProxy
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 10:07
 **/
// 代理对象
public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象 生成一个代理对象
    public Object getProxyInstance() {
        /**
         *
         *  @param ClassLoader loader : 指定当前目标对象使用的类加载器， 获取类加载器的方法固定
         *  @param Class<?>[] interfaces : 目标对象实现的接口类型，使用泛型方法确认类型
         *  @param InvocationHandler h :  事件处理， 执行目标对象方法时， 会触发事件处理器方法
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK代理开始...");
                        // 反射机制调用目标方法
                        return method.invoke(target, args);
                    }
                });
    }

}
