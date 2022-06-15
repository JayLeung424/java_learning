package com.study.code.design_pattern.patterns.bridge;

/**
 * @ClassName: Vivo
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 13:41
 **/
public class Vivo implements Brand {
    @Override
    public void open() {
        System.out.println("Vivo手机开机了");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机关机了");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机拨打电话");
    }
}
