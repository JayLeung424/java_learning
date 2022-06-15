package com.study.code.design_pattern.patterns.bridge;

/**
 * @ClassName: Phone
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 13:42
 **/
public abstract class Phone {

    // 品牌
    private Brand brand;

    // 构造器
    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open() {
        this.brand.open();
    }

    protected void close() {
        this.brand.close();
    }

    protected void call() {
        this.brand.call();
    }
}
