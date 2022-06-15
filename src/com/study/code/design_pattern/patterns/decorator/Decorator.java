package com.study.code.design_pattern.patterns.decorator;

/**
 * @ClassName: Decorator
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:46
 **/
public class Decorator extends Drink {
    private Drink coffee;

    public Decorator(Drink coffee) { // 组合
        this.coffee = coffee;
    }

    @Override
    public float cost() {
        // super.getPrice() - 自己的价格
        return super.getPrice() + coffee.cost();
    }

    @Override
    public String getDesc() {
        // coffee.getDesc() 被装饰者的信息
        return super.desc + " " + super.getPrice() + " && " + coffee.getDesc();
    }
}
