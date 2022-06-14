package com.design.patterns.adapter.class_adapter;

/**
 * @ClassName: Phone
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 17:30
 **/
// 目标
public class Phone {
    // 充电
    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("电压为5V, 开始充电");
        } else {
            System.out.println("电压不是5V，无法充电");
        }
    }
}

