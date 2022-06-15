package com.study.code.design_pattern.patterns.iterator;

import java.util.Iterator;

/**
 * @ClassName: ComputerCollegeIterator
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 10:25
 **/
public class ComputerCollegeIterator implements Iterator {
    // 数组的方式
    Department[] departments;
    // 位置
    int position = 0;

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    /**
     * 判断是否还有下一个元素
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        if (position >= departments.length || departments[position] == null) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        Department department = departments[position];
        position++;
        return department;
    }

    // 空实现
    @Override
    public void remove() {

    }
}
