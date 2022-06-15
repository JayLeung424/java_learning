package com.study.code.design_pattern.patterns.proxy.static_proxy;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 10:29
 **/
public class Client {

    public static void main(String[] args) {
        // 创建目标对象 - 被代理对象
        TeacherDao teacherDao = new TeacherDao();

        // 创建代理对象
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);

        // 通过代理对象， 调用到被代理对象的方法
        teacherDaoProxy.teach();
    }
}
