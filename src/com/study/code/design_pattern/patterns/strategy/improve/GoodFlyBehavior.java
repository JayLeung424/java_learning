package com.study.code.design_pattern.patterns.strategy.improve;

/**
 * @ClassName: GoodFlyBehavior
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:42
 **/
public class GoodFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("特别擅长飞行... ");
    }
}

