package com.study.code.design_pattern.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: College
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/7 18:00
 **/
public class College extends OrganizationComponent {
    // 存放实际上是Department
    List<OrganizationComponent> componentList = new ArrayList<>();

    public College(String name, String desc) {
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
