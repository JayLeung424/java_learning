package com.design.patterns.factory.abstract_factory.order;


/**
 * @ClassName: PizzaStore
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 17:19
 **/
public class PizzaStore {
    public static void main(String[] args) {
        String local = "beijing";
        if ("beijing".equals(local)) {
            // 创建北京口味的pizza
            new OrderPizza(new BeijingFactory());
        } else if ("london".equals(local)) {
            new OrderPizza(new LondonFactory());
        }
    }
}
