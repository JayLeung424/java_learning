package com.design.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: University
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/7 17:53
 **/
// 就是Composite
public class University extends OrganizationComponent {
    // 存放实际上是College
    List<OrganizationComponent> componentList = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent component) {
        componentList.add(component);
    }

    @Override
    protected void remove(OrganizationComponent component) {
        componentList.remove(component);
    }

    @Override
    public void display() {
        System.out.println("--------------" + getName() + "--------------");
        for (OrganizationComponent organizationComponent : componentList) {
            organizationComponent.display();
        }
    }

}
