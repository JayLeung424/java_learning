package com.design.patterns.strategy.improve;

/**
 * @ClassName: BadFlyBehavior
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:42
 **/
public class BadFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("不太擅长飞行... ");
    }
}
