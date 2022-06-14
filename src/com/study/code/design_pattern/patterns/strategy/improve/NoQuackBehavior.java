package com.design.patterns.strategy.improve;

/**
 * @ClassName: NoQuackBehavior
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:44
 **/
public class NoQuackBehavior implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("鸭子不会叫～～～");
    }
}
