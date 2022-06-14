package com.design.patterns.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: InfoCollegeIterator
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 10:27
 **/
public class InfoCollegeIterator implements Iterator {
    // 集合的方式实现
    List<Department> departmentList;
    // 索引 集合默认-1开始
    int index = -1;

    public InfoCollegeIterator(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public boolean hasNext() {
        if (index >= departmentList.size() - 1) {
            return false;
        }
        // 因为索引从-1开始, 所以如果有下一个的话 那么索引+1
        index++;
        return true;
    }

    @Override
    public Object next() {
        return departmentList.get(index);
    }

    @Override
    public void remove() {

    }
}
