package com.study.code.design_pattern.patterns.factory.abstract_factory.order;


import com.study.code.design_pattern.patterns.factory.abstract_factory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @ClassName: OrderPizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 14:19
 **/
public class OrderPizza {

    // 聚合一个abstractFactory
    AbstractFactory abstractFactory;

    public OrderPizza(AbstractFactory abstractFactory) {
        setAbstractFactory(abstractFactory);
    }

    private void setAbstractFactory(AbstractFactory abstractFactory) {
        Pizza pizza = null;
        this.abstractFactory = abstractFactory;

        String orderType = "";
        do {
            orderType = getPizzaType();
            // 抽象的方法, 由工厂子类完成
            pizza = abstractFactory.createPizza(orderType);
            // 输出pizza
            if (Objects.nonNull(pizza)) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                break;
            }
        } while (true);
    }

    // 获取客户订购的Pizza的类型
    private String getPizzaType() {
        try {
            BufferedReader strIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input pizza type:");
            return strIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
