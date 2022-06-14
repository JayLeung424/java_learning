package com.design.patterns.composite;

import java.util.ArrayList;
import java.util.List;

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
