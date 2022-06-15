package com.study.code.design_pattern.patterns.bridge;

/**
 * @ClassName: UpRightPhone
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 13:45
 **/
public class UpRightPhone extends Phone {
    public UpRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        super.open();
        System.out.println("直立样式的手机");
    }

    @Override
    public void close() {
        super.close();
        System.out.println("直立样式的手机");
    }

    @Override
    public void call() {
        super.call();
        System.out.println("直立样式的手机");
    }
}
