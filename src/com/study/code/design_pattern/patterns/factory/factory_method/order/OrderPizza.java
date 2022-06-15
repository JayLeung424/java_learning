package com.study.code.design_pattern.patterns.factory.factory_method.order;


import com.study.code.design_pattern.patterns.factory.factory_method.pizza.Pizza;

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
public abstract class OrderPizza {

    private Pizza pizza = null;

    // 定义一个抽象的方法 createPizza 让自己的工厂子类 自己实现
    abstract Pizza createPizza(String orderType);

    public OrderPizza() {
        System.out.println("使用简单工厂模式。");
        String orderType = "";
        do {
            orderType = getPizzaType();
            // 抽象的方法, 由工厂子类完成
            pizza = this.createPizza(orderType);
            // 输出pizza
            if (Objects.nonNull(pizza)) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }
        } while (true);
    }

    // 获取客户订购的Pizza的类型
    private String getPizzaType() {
        try {
            BufferedReader strIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input pizza type:");
            String str = strIn.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
