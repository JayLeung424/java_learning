package com.study.code.design_pattern.patterns.factory.abstract_factory.order;

import com.study.code.design_pattern.patterns.factory.abstract_factory.pizza.Pizza;

// 一个抽象工厂模式的抽象层
public interface AbstractFactory {

    // 让下面工厂子类来具体实现
    Pizza createPizza(String orderType);
}
