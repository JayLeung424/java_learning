package com.design.patterns.factory.factory_method.order;

import com.design.patterns.factory.factory_method.pizza.*;

/**
 * @ClassName: LondonOrderPizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 17:13
 **/
public class LondonOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("pepper".equals(orderType)) {
            pizza = new LondonPepperPizza();
        } else if ("cheese".equals(orderType)) {
            pizza = new LondonCheesePizza();
        }
        return pizza;
    }
}
