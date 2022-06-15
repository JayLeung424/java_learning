package com.study.code.design_pattern.patterns.factory.factory_method.pizza;

/**
 * @ClassName: CheesePizza
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 14:16
 **/
// 奶酪Pizza
public class LondonPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦胡椒Pizza");
        System.out.println("准备伦敦胡椒Pizza原材料");
    }
}
