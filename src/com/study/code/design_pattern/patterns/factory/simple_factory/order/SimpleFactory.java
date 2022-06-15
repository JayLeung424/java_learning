package com.study.code.design_pattern.patterns.factory.simple_factory.order;

import com.study.code.design_pattern.patterns.factory.simple_factory.pizza.CheesePizza;
import com.study.code.design_pattern.patterns.factory.simple_factory.pizza.GreekPizza;
import com.study.code.design_pattern.patterns.factory.simple_factory.pizza.PepperPizza;
import com.study.code.design_pattern.patterns.factory.simple_factory.pizza.Pizza;

/**
 * @ClassName: SimpleFactory
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 16:04
 **/
// 简单工厂类
public class SimpleFactory {
    // 根据orderType返回对应的Pizza对象
    public Pizza createPizza(String orderType) {
        System.out.println("使用简单工厂模式。");
        Pizza pizza = null;
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊Pizza");
        } else if ("cheese".equals(orderType)) {
            pizza = new CheesePizza();
            pizza.setName("奶酪Pizza");
        } else if ("pepper".equals(orderType)) {
            pizza = new PepperPizza();
            pizza.setName("胡椒Pizza");
        } else {
            System.out.println("目前没有这种类型的Pizza");
        }
        return pizza;
    }

    // 根据orderType返回对应的Pizza对象
    public static Pizza createPizza2(String orderType) {
        System.out.println("使用简单工厂模式2");
        Pizza pizza = null;
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊Pizza");
        } else if ("cheese".equals(orderType)) {
            pizza = new CheesePizza();
            pizza.setName("奶酪Pizza");
        } else if ("pepper".equals(orderType)) {
            pizza = new PepperPizza();
            pizza.setName("胡椒Pizza");
        } else {
            System.out.println("目前没有这种类型的Pizza");
        }
        return pizza;
    }
}
