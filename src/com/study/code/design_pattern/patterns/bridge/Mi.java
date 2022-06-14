package com.design.patterns.bridge;

/**
 * @ClassName: Mi
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 13:39
 **/
public class Mi implements Brand {
    @Override
    public void open() {
        System.out.println("Mi手机开机了");
    }

    @Override
    public void close() {
        System.out.println("Mi手机关机了");
    }

    @Override
    public void call() {
        System.out.println("Mi手机拨打电话");
    }
}
