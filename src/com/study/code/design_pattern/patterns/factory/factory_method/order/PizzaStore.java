package com.design.patterns.factory.factory_method.order;

/**
 * @ClassName: PizzaStore
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 17:19
 **/
public class PizzaStore {
    public static void main(String[] args) {
        // 本地 修改为可输入
        String local = "beijing";
        if ("beijing".equals(local)) {
            // 创建北京口味的pizza
            new BeijingOrderPizza();
        } else if ("london".equals(local)) {
            new LondonOrderPizza();
        }
    }
}
