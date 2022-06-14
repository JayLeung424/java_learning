package com.design.patterns.strategy.improve;

/**
 * @ClassName: ToyDuck
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:17
 **/
public class ToyDuck extends Duck {
    public ToyDuck() {
        flyBehavior = new NoFlyBehavior();
        quackBehavior = new GeGeQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是一只玩具鸭子");

    }
}
