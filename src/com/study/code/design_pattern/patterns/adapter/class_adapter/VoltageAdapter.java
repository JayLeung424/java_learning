package com.study.code.design_pattern.patterns.adapter.class_adapter;

/**
 * @ClassName: VoltageAdapter
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 17:30
 **/
// 适配器类
public class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        // 获取到220v的电压
        int src = output220V();
        // 获取到需要的电压 220/44=5V
        int dstV = src / 44;
        return dstV;
    }
}
