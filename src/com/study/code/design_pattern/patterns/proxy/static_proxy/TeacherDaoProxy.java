package com.design.patterns.proxy.static_proxy;

/**
 * @ClassName: TeacherDaoProxy
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 10:07
 **/
// 代理对象
public class TeacherDaoProxy implements ITeacherDao {
    private TeacherDao teacherDao;

    public TeacherDaoProxy(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void teach() {
        System.out.println("开始代理");
        teacherDao.teach();
        System.out.println("代理结束");
    }
}
