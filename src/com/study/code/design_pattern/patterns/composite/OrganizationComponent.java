package com.design.patterns.composite;

/**
 * @ClassName: OrganizationComponent
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/7 17:49
 **/
public abstract class OrganizationComponent {
    private String name; // 名字
    private String desc; // 说明

    public OrganizationComponent(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    protected void add(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    public abstract void display();

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
