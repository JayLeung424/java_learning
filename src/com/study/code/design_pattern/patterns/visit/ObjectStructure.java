package com.design.patterns.visit;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: ObjectStructure
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/14 13:05
 **/
// 数据结构， 管理很多人（Man , Woman）
public class ObjectStructure {
    // 维护了一个集合
    List<Person> personList = new LinkedList<>();

    public void attach(Person person) {
        personList.add(person);
    }

    public void detach(Person person) {
        personList.remove(person);
    }

    // 显示测评情况
    public void display(Action action) {
        for (Person p : personList) {
            p.accept(action);
        }
    }
}
