package com.design.patterns.proxy.static_proxy;

/**
 * @ClassName: TeacherDao
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 10:06
 **/
public class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("老师授课中");
    }
}
