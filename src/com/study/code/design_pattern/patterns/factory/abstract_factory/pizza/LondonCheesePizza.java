package com.design.patterns.factory.abstract_factory.pizza;

/**
 * @ClassName: CheesePizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 14:16
 **/
// 奶酪Pizza
public class LondonCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦奶酪Pizza");
        System.out.println("准备伦敦奶酪Pizza原材料");
    }
}
