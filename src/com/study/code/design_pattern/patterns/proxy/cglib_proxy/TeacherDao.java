package com.design.patterns.proxy.cglib_proxy;

import com.design.patterns.proxy.static_proxy.ITeacherDao;

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
