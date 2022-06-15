package com.study.code.design_pattern.patterns.strategy.improve;


/**
 * @ClassName: WildDuck
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:17
 **/
public class WildDuck extends Duck {
    public WildDuck() {
        flyBehavior = new GoodFlyBehavior();
        quackBehavior = new GeGeQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是一只野鸭子！");
    }
}
