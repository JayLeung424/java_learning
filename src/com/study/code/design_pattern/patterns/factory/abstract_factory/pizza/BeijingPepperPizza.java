package com.study.code.design_pattern.patterns.factory.abstract_factory.pizza;

/**
 * @ClassName: GreekPizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 14:17
 **/
// 希腊Pizza
public class BeijingPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京胡椒Pizza");
        System.out.println("准备北京胡椒Pizza原材料");
    }
}
