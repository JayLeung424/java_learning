package com.study.code.design_pattern.patterns.strategy.improve;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:52
 **/
public class Client {
    public static void main(String[] args) {
        WildDuck wildDuck = new WildDuck();
        wildDuck.quack();
        wildDuck.fly();
        wildDuck.display();

        // 改变能力
        wildDuck.setFlyBehavior(new NoFlyBehavior());
        wildDuck.fly();

    }
}
