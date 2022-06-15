package com.study.code.design_pattern.patterns.proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: TeacherDaoProxy
 * @Description: 代理对象
 * @Author: jie liang
 * @Date: 2022/4/12 10:07
 **/
public class ProxyFactory implements MethodInterceptor {
    // 维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 返回一个代理对象 是target对象的代理对象
     *
     * @return
     */
    public Object getProxyInstance() {
        // 创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类 - target.class
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数 - 自己
        enhancer.setCallback(this);
        // 创建子类对象， 即代理对象
        return enhancer.create();
    }

    /**
     * 重写 intercept 方法， 回调用目标对象的方法
     *
     * @param o
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib 代理模式 ～～ 开始");
        Object returnVal = method.invoke(target, args);
        System.out.println("Cglib 代理模式 ～～ 提交");
        return returnVal;
    }
}
