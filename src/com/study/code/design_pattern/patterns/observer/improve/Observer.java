package com.study.code.design_pattern.patterns.observer.improve;

/**
 * @ClassName: Observer
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 19:48
 **/
public interface Observer {
    // 参数为要修改的 温度 气压 湿度
    public void update(float temperature, float pressure, float humidity);
}
