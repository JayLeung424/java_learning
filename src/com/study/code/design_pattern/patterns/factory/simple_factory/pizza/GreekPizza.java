package com.design.patterns.factory.simple_factory.pizza;

/**
 * @ClassName: GreekPizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 14:17
 **/
// 希腊Pizza
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备希腊Pizza原材料");
    }
}
