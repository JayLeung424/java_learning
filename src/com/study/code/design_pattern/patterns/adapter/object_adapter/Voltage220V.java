package com.design.patterns.adapter.object_adapter;

/**
 * @ClassName: Voltage220V
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 17:20
 **/
public class Voltage220V {
    public int output220V() {
        int src = 220;
        System.out.println("电压= " + src + "V");
        return src;
    }
}
