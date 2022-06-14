package com.design.patterns.factory.simple_factory.pizza;

// 讲Pizza类做成抽象
public abstract class Pizza {
    protected String name; // 名字

    // 准备原材料, 不同的pizza不一样, 所以设计为抽象方法
    public abstract void prepare();


    public void bake() {
        System.out.println(name + " baking;");
    }

    public void cut() {
        System.out.println(name + " cutting;");
    }

    // 打包
    public void box() {
        System.out.println(name + " boxing;");
    }

    public void setName(String name) {
        this.name = name;
    }
}
