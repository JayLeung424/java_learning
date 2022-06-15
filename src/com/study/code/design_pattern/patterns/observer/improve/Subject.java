package com.study.code.design_pattern.patterns.observer.improve;


/**
 * @ClassName: Subject
 * @Description: 接口  让WeatherData 来实现
 * @Author: jiel
 * @Date: 2022/4/15 19:46
 **/
public interface Subject {

    public void registerObserver(Observer observer);

    public void remove(Observer observer);

    public void notifyObservers();
}
