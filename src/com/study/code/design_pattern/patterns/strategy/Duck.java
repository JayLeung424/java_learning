package com.design.patterns.strategy;

/**
 * @ClassName: Duck
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:15
 **/
public abstract class Duck {

    public Duck() {
    }
    // 显示鸭子的信息
    public abstract void dispaly();

    public void quack() {
        System.out.println("鸭子嘎嘎叫~~");
    }

    public void swim() {
        System.out.println("鸭子会游泳~~");
    }

    public void fly() {
        System.out.println("鸭子会飞翔~~");
    }
}
