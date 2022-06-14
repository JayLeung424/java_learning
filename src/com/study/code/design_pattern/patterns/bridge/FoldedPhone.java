package com.design.patterns.bridge;

/**
 * @ClassName: FoldedPhone
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 13:44
 **/
public class FoldedPhone extends Phone {
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        super.open();
        System.out.println("折叠样式的手机");
    }

    @Override
    public void close() {
        super.close();
        System.out.println("折叠样式的手机");
    }

    @Override
    public void call() {
        super.call();
        System.out.println("折叠样式的手机");
    }
}
