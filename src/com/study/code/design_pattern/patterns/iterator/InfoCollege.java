package com.study.code.design_pattern.patterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: InfoCollege
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 10:46
 **/
public class InfoCollege implements Collage {
    List<Department> departmentList;

    public InfoCollege() {
        departmentList = new ArrayList<>();
        addDepartment(" 信息安全专业", " 信息安全专业 ");
        addDepartment(" 网络安全专业", " 网络安全专业 ");
        addDepartment(" 服务器安全专业", " 服务器安全专业 ");
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departmentList.add(new Department(name, desc));
    }

    @Override
    public Iterator createIterator() {
        return new InfoCollegeIterator(departmentList);
    }
}
