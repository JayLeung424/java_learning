package com.study.code.design_pattern.patterns.factory.abstract_factory.pizza;

/**
 * @ClassName: BeiJingCheesePizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 17:04
 **/
public class BeijingCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京奶酪Pizza");
        System.out.println("准备北京奶酪Pizza原材料");
    }
}

