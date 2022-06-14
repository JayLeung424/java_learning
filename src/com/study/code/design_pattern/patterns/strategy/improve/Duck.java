package com.design.patterns.strategy.improve;

import java.util.Objects;

/**
 * @ClassName: Duck
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:15
 **/
public abstract class Duck {
    // 属性 策略接口
    FlyBehavior flyBehavior;

    QuackBehavior quackBehavior;

    public Duck() {
    }

    // 显示鸭子信息
    public abstract void display();

    public void swim() {
        System.out.println("鸭子会游泳~~");
    }

    public void fly(){
        // 改进
        if (Objects.nonNull(flyBehavior)){
            flyBehavior.fly();
        }
    }

    public void quack(){
        // 改进
        if (Objects.nonNull(quackBehavior)){
            quackBehavior.quack();
        }
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
