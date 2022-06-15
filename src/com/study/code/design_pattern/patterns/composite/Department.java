package com.study.code.design_pattern.patterns.composite;

/**
 * @ClassName: Department
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/7 17:21
 **/
public class Department extends OrganizationComponent {

    public Department(String name, String desc) {
        super(name, desc);
    }

    @Override
    public void display() {
        System.out.println("--------------" + getName() + "--------------");
    }

}
