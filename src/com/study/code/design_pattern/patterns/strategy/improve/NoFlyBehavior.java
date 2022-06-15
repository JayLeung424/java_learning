package com.study.code.design_pattern.patterns.strategy.improve;

/**
 * @ClassName: NoFlyBehavior
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:42
 **/
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞行... ");
    }
}
