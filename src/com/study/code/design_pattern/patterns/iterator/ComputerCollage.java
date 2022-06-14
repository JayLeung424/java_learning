package com.design.patterns.iterator;

import java.util.Iterator;

/**
 * @ClassName: ComputerCollage
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 10:41
 **/
public class ComputerCollage implements Collage {
    // 创建和 ComputerCollegeIterator 一样的属性
    Department[] departments;
    // 保存当前数组的对象个数
    int numOfDepartment = 0;

    public ComputerCollage() {
        // 限制数组长度
        departments = new Department[5];
        // 初始化
        addDepartment("Java专业", " Java专业 ");
        addDepartment("PHP专业", " PHP专业 ");
        addDepartment("大数据专业", " 大数据专业 ");
    }

    @Override
    public String getName() {
        return "计算机科学与技术学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Department department = new Department(name, desc);
        departments[numOfDepartment] = department;
        numOfDepartment++;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(this.departments);
    }
}
