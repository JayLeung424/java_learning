package com.study.code.design_pattern.patterns.factory.simple_factory.pizza;

/**
 * @ClassName: CheesePizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 14:16
 **/
// 奶酪Pizza
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备奶酪Pizza原材料");
    }
}
