package com.study.code.design_pattern.patterns.factory.abstract_factory.order;

import com.study.code.design_pattern.patterns.factory.abstract_factory.pizza.BeijingCheesePizza;
import com.study.code.design_pattern.patterns.factory.abstract_factory.pizza.BeijingPepperPizza;
import com.study.code.design_pattern.patterns.factory.abstract_factory.pizza.Pizza;

/**
 * @ClassName: BeijingFactory
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 17:49
 **/
public class BeijingFactory implements AbstractFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("pepper".equals(orderType)) {
            pizza = new BeijingPepperPizza();
        } else if ("cheese".equals(orderType)) {
            pizza = new BeijingCheesePizza();
        }
        return pizza;
    }
}
