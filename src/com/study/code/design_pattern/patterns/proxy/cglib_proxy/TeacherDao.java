package com.study.code.design_pattern.patterns.proxy.cglib_proxy;


/**
 * @ClassName: TeacherDao
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 10:06
 **/
public class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("老师授课中，我是cglib代理，不需要实现接口");
    }
}
