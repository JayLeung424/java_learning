package com.study.code.design_pattern.patterns.iterator;

/**
 * @ClassName: Department
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 10:23
 **/
public class Department {
    // 名字
    private String name;
    // 描述
    private String desc;

    public Department(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
