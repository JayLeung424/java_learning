package com.design.patterns.observer.improve;

public class Client {
    public static void main(String[] args) {

        // 创建 WeatherData 对象
        WeatherData weatherData = new WeatherData();

        // 创建观察者
        CurrentConditions currentConditions = new CurrentConditions();

        // 注册到weatherData中
        weatherData.registerObserver(currentConditions);
        // 创建新的观察者 并注册
        weatherData.registerObserver(new BaiduSite());

        // 更新天气数据
        weatherData.setData(30, 150, 40);

    }
}
