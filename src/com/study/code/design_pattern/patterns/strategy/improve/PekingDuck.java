package com.study.code.design_pattern.patterns.strategy.improve;


/**
 * @ClassName: PekingDuck
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:18
 **/
public class PekingDuck extends Duck {

    public PekingDuck() {
        flyBehavior = new NoFlyBehavior();
        quackBehavior = new NoQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是一只北京鸭子");
    }

}
