package com.study.code.design_pattern.patterns.factory.simple_factory.order;

import com.study.code.design_pattern.patterns.factory.simple_factory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @ClassName: OrderPizza2
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 16:37
 **/
public class OrderPizza2 {

    private Pizza pizza = null;

    public OrderPizza2() {
        do {
            String orderType = getPizzaType();
            pizza = SimpleFactory.createPizza2(orderType);
            // 输出pizza
            if (Objects.nonNull(pizza)) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购pizza失败,失败原因:没有该pizza");
                break;
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
