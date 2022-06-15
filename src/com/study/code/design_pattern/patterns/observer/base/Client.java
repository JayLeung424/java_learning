package com.study.code.design_pattern.patterns.observer.base;

public class Client {
    public static void main(String[] args) {
        // 创建接入方 currentConditions
        CurrentConditions currentConditions = new CurrentConditions();
        // 创建 WeatherData 对象 并将接入方 currentConditions 传递到 WeatherData中
        WeatherData weatherData = new WeatherData(currentConditions);

        // 更新天气数据
        weatherData.setData(30, 150, 40);

        System.out.println("============天气情况变化=============");
        weatherData.setData(40, 160, 20);
    }
}
