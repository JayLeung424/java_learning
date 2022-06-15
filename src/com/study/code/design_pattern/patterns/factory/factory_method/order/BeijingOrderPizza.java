package com.study.code.design_pattern.patterns.factory.factory_method.order;

import com.study.code.design_pattern.patterns.factory.factory_method.pizza.BeijingCheesePizza;
import com.study.code.design_pattern.patterns.factory.factory_method.pizza.BeijingPepperPizza;
import com.study.code.design_pattern.patterns.factory.factory_method.pizza.Pizza;

/**
 * @ClassName: BeiJingOrderPizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 17:13
 **/
class BeijingOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("pepper".equals(orderType)) {
            pizza = new BeijingPepperPizza();
        } else if ("cheese".equals(orderType)) {
            pizza = new BeijingCheesePizza();
        }
        return pizza;
    }
}
