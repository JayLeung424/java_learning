package com.study.code.design_pattern.patterns.observer.base;

/**
 * 类是核心
 * 1. 包含最新的天气情况信息
 * 2. 含有 CurrentConditions 对象 目的是推送
 * 当数据有更新的时候，就主动的调用 CurrentConditions.update()方法 (含display).这样他们(接入方)就可以看到最新的消息
 *
 * @author jiel
 */
public class WeatherData {
    private float temperature;
    private float pressure;
    private float humidity;
    private CurrentConditions currentConditions;

    public WeatherData(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void dataChange() {
        // 调用接入方的update
        currentConditions.update(getTemperature(), getPressure(), getHumidity());
    }

    /**
     * 当数据有更新的时候， 就调用setData
     *
     * @param temperature
     * @param pressure
     * @param humidity
     */
    public void setData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        // 调用DataChange, 将最新的消息推送给接入方 - currentConditions
        dataChange();
    }
}
