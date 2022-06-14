package com.design.patterns.observer.improve;

/**
 * @ClassName: Baidu
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 22:29
 **/
public class BaiduSite implements Observer {
    // 温度
    private float temperature;
    // 气压
    private float pressure;
    // 湿度
    private float humidity;

    /**
     * 更新天气情况， 是由WeatherData来调用 我使用推送模式
     *
     * @param temperature
     * @param pressure
     * @param humidity
     */
    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    /**
     * 显示
     *
     * @return
     */
    public void display() {
        System.out.println("Baidu***Today mTemperature: " + temperature + "***");
        System.out.println("Baidu***Today mPressure: " + pressure + "***");
        System.out.println("Baidu***Today mHumidity: " + humidity + "***");
    }

}
