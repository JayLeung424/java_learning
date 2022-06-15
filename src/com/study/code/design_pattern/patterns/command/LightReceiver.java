package com.study.code.design_pattern.patterns.command;

/**
 * @ClassName: LightReceiver
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 21:18
 **/
public class LightReceiver {

    public void on() {
        System.out.println("电灯打开了");
    }

    public void off() {
        System.out.println("电灯关闭了");
    }
}
