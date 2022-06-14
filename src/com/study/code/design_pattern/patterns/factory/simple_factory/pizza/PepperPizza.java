package com.design.patterns.factory.simple_factory.pizza;

/**
 * @ClassName: PepperPizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 15:13
 **/
public class PepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备胡椒Pizza原材料");
    }
}
