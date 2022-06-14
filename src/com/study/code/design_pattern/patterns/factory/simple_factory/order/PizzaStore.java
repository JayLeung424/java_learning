package com.design.patterns.factory.simple_factory.order;

/**
 * @ClassName: PizzaStore
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 14:28
 **/
// Pizza商店
public class PizzaStore {
    public static void main(String[] args) {
        // 客户端  完成订购任务
        OrderPizza orderPizza = new OrderPizza(new SimpleFactory());
    }

}
