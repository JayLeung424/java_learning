package com.study.code.design_pattern.patterns.strategy;

/**
 * @ClassName: ToyDuck
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:17
 **/
public class ToyDuck extends Duck {
    @Override
    public void dispaly() {
        System.out.println("这是一只玩具鸭子");
    }

    // 需要重写父类的所有方法

    @Override
    public void quack() {
        System.out.println("鸭子不会叫~~");
    }

    @Override
    public void swim() {
        System.out.println("鸭子不会游泳~~");
    }

    @Override
    public void fly() {
        System.out.println("鸭子不会飞翔~~");
    }
}
