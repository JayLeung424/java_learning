package com.study.code.design_pattern.patterns.adapter.object_adapter;

/**
 * @ClassName: VoltageAdapter
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 17:30
 **/
// 适配器类
public class VoltageAdapter implements IVoltage5V {
    Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        // 定义需要的电压 默认为0
        int dstV = 0;
        // 判断voltage220V对象是否存在
        if (null != voltage220V) {
            // 获取到220v的电压
            int src = voltage220V.output220V();
            System.out.println("使用对象适配器进行转换");
            dstV = src / 44;
            System.out.println("适配完成,输出的电压为=" + dstV);
        }
        return dstV;
    }
}
