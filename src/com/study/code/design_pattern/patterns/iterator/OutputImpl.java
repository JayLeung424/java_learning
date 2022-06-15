package com.study.code.design_pattern.patterns.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: OutputImpl
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 10:49
 **/
public class OutputImpl {
    // 学院集合
    List<Collage> collageList;

    public OutputImpl(List<Collage> collageList) {
        this.collageList = collageList;
    }

    //遍历所有学院,然后调用 printDepartment 输出各个学院的系
    public void printCollege() {
        // 从collegeList 取出所有学院, Java 中的 List 已经实现 Iterator
        Iterator<Collage> iterator = collageList.iterator();
        while (iterator.hasNext()) {
            Collage collage = iterator.next();
            System.out.println(" ------ " + collage.getName() + " -----");
            printDepartment(collage.createIterator());
        }
    }

    public void printDepartment(Iterator iterator) {
        while (iterator.hasNext()) {
            Department department = (Department) iterator.next();
            System.out.println(department.getName());
        }
    }
}
