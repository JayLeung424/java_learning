package com.design.patterns.factory.simple_factory.order;

import com.design.patterns.factory.simple_factory.pizza.Pizza;

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
    // 构造器
//    public OrderPizza() {
//        Pizza pizza = null;
//        // 订购pizza的类型
//        String orderType = "";
//
//        do {
//            orderType = getPizzaType();
//            if ("greek".equals(orderType)){
//                pizza = new GreekPizza();
//                pizza.setName("希腊Pizza");
//            }else if ("cheese".equals(orderType)){
//                pizza = new CheesePizza();
//                pizza.setName("奶酪Pizza");
//            }else if ("pepper".equals(orderType)){
//                pizza = new PepperPizza();
//                pizza.setName("胡椒Pizza");
//            }else {
//                System.out.println("目前没有这种类型的Pizza");
//                break;
//            }
//
//            // pizza 制作过程
//            pizza.prepare();
//            pizza.bake();
//            pizza.cut();
//            pizza.box();
//        }while (true);
//    }

    // 新玩法 定义一个简单工厂对象
    private SimpleFactory simpleFactory;

    private Pizza pizza = null;

    public OrderPizza(SimpleFactory simpleFactory) {
        setSimpleFactory(simpleFactory);
    }

    public void setSimpleFactory(SimpleFactory simpleFactory) {
        // 用户输入的pizza类型
        String orderType = "";
        // 设置简单工厂对象
        this.simpleFactory = simpleFactory;
        do {
            orderType = getPizzaType();
            pizza = this.simpleFactory.createPizza(orderType);
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
