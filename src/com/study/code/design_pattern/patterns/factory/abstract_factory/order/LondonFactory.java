package com.design.patterns.factory.abstract_factory.order;

import com.design.patterns.factory.abstract_factory.pizza.LondonCheesePizza;
import com.design.patterns.factory.abstract_factory.pizza.LondonPepperPizza;
import com.design.patterns.factory.abstract_factory.pizza.Pizza;

/**
 * @ClassName: BeijingFactory
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 17:47
 **/
// 工厂子类
public class LondonFactory implements AbstractFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("pepper".equals(orderType)) {
            pizza = new LondonPepperPizza();
        } else if ("cheese".equals(orderType)) {
            pizza = new LondonCheesePizza();
        }
        return pizza;
    }
}
